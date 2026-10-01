"""Language switch for the Vietnamese site.

Only Tiếng Việt is built and hosted here (/vi/). The 中文 and English entries
of the language selector open the same page on the upstream site
(extra.upstream_site, https://leetcode.doocs.org by default); a page without a
zh/en counterpart (e.g. /vi/lcci/) falls back to that language's home.

- <a hreflang=vi> becomes page-relative; zh/en become absolute upstream URLs.
- <link rel=alternate hreflang> keeps only the vi page itself (the upstream site
  does not link back, so zh/en alternates would not be reciprocal) and is
  dropped on untranslated stubs.
- Untranslated stubs are removed from sitemap.xml.
"""

import gzip
import json
import os
import re
import sys
from pathlib import Path
from xml.etree import ElementTree as ET

_HOOKS_DIR = Path(__file__).resolve().parent
if str(_HOOKS_DIR) not in sys.path:
    sys.path.insert(0, str(_HOOKS_DIR))

import stay_on_page as sop  # noqa: E402

LANGS = ("zh", "en", "vi")
_PREFIX = {"zh": "", "en": "en", "vi": "vi"}
_DOCS = {"zh": "docs", "en": "docs-en", "vi": "docs-vi"}
DEFAULT_UPSTREAM = "https://leetcode.doocs.org"
STATUS_FILE = ".vi_status.json"
STUB = "stub"
_SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9"

_status_cache = {}

_TAG_HREF = re.compile(
    r"""
    (?P<prefix>
        <(?P<tag>a|link)\b
        (?=[^>]*\bhreflang=(?P<lq>["']?)(?P<lang>zh|en|vi)(?P=lq)(?=[\s>/]))
        [^>]*\bhref=(?P<hq>["']?)
    )
    [^"'\s>]*
    (?P<suffix>(?P=hq))
    """,
    re.IGNORECASE | re.VERBOSE,
)


def _link_tag(lang):
    return re.compile(
        rf"""<link\b(?=[^>]*\bhreflang=(["']?){lang}\1(?=[\s>/]))[^>]*>""",
        re.IGNORECASE,
    )


def _config_get(config, key, default=None):
    if isinstance(config, dict):
        return config.get(key, default)
    return getattr(config, key, default)


def upstream_site(config) -> str:
    extra = _config_get(config, "extra") or {}
    return str(extra.get("upstream_site") or DEFAULT_UPSTREAM).rstrip("/")


def site_root_url(config) -> str:
    """Origin (and base path) the /vi/ site is served under."""
    url = str(_config_get(config, "site_url") or "").rstrip("/")
    return url[:-3] if url.endswith("/vi") else url


def _docs_root(config) -> Path:
    return Path(str(_config_get(config, "docs_dir") or "docs-vi")).parent


def abs_path(rel: str, lang: str) -> str:
    parts = [p for p in (_PREFIX[lang], rel.strip("/")) if p]
    path = "/" + "/".join(parts)
    return path if path == "/" else path + "/"


def vi_status(config) -> dict:
    path = _docs_root(config) / _DOCS["vi"] / STATUS_FILE
    key = str(path)
    if key not in _status_cache:
        try:
            _status_cache[key] = json.loads(path.read_text(encoding="utf-8"))
        except (OSError, ValueError):
            _status_cache[key] = {}
    return _status_cache[key]


def has_page(config, lang: str, rel: str) -> bool:
    """docs/ and docs-en/ are the upstream pages flattened by build_site.py."""
    return sop._markdown_exists(_docs_root(config) / _DOCS[lang], rel)


def is_stub(config, rel: str) -> bool:
    rel = rel.strip("/")
    key = f"{rel}.md" if rel else "index.md"
    return vi_status(config).get(key) == STUB


def rewrite(output: str, rel: str, config) -> str:
    upstream = upstream_site(config)
    self_url = site_root_url(config) + abs_path(rel, "vi")

    def repl(match):
        lang = match.group("lang").lower()
        if match.group("tag").lower() == "link":
            href = self_url  # zh/en <link> tags are removed below
        elif lang == "vi":
            href = "./"
        else:
            target = rel if has_page(config, lang, rel) else ""
            href = upstream + abs_path(target, lang)
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    output = _TAG_HREF.sub(repl, output)
    drop = ("zh", "en", "vi") if is_stub(config, rel) else ("zh", "en")
    for lang in drop:
        output = _link_tag(lang).sub("", output)
    if "XMLHttpRequest.prototype.open" not in output:
        # Same patch as upstream: Material's selector then follows the href.
        output = sop._inject_head(output, [sop._XHR_PATCH])
    return output


def on_post_page(output, page, config):
    if not output:
        return output
    try:
        return rewrite(output, sop._page_rel(page), config)
    except Exception as e:
        # VI_STRICT=1 (set by the CI/Vercel builds) turns a silently wrong
        # language switch into a failed build, e.g. after an engine bump.
        if os.environ.get("VI_STRICT") == "1":
            raise
        print(f"Error in vi_switch hook: {e}")
        return output


def drop_stubs_from_sitemap(xml_text: str, config) -> str:
    ET.register_namespace("", _SITEMAP_NS)
    root = ET.fromstring(xml_text)
    base = str(_config_get(config, "site_url") or "").rstrip("/") + "/"
    for url_el in list(root.findall(f"{{{_SITEMAP_NS}}}url")):
        loc = (url_el.findtext(f"{{{_SITEMAP_NS}}}loc") or "").strip()
        if loc.startswith(base) and is_stub(config, loc[len(base) :]):
            root.remove(url_el)
    return ET.tostring(root, encoding="utf-8", xml_declaration=True).decode("utf-8")


def on_post_build(config):
    sitemap = Path(str(_config_get(config, "site_dir") or "")) / "sitemap.xml"
    if not sitemap.is_file():
        return
    text = drop_stubs_from_sitemap(sitemap.read_text(encoding="utf-8"), config)
    sitemap.write_text(text, encoding="utf-8")
    gz = sitemap.with_name("sitemap.xml.gz")
    if gz.exists():
        gz.write_bytes(gzip.compress(text.encode("utf-8"), mtime=0))
