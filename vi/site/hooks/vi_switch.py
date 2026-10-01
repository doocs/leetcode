"""Language switch across the 中文 / English / Tiếng Việt sites of the fork.

Runs on all three sites (config extra.site_lang = zh | en | vi). On zh/en the
upstream stay_on_page hook keeps handling zh/en, so only the vi entries are
rewritten here; on the vi site every language entry is rewritten.

- <a hreflang> (language selector) becomes page-relative so the site works under
  any base path; a missing counterpart falls back to that language's home.
- <link rel=alternate hreflang> becomes absolute and is dropped when the
  counterpart is missing or is an untranslated vi stub.
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
STATUS_FILE = ".vi_status.json"
STUB = "stub"
_SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9"

_status_cache = {}


def _tag_href(langs):
    alt = "|".join(langs)
    return re.compile(
        rf"""
        (?P<prefix>
            <(?P<tag>a|link)\b
            (?=[^>]*\bhreflang=(?P<lq>["']?)(?P<lang>{alt})(?P=lq)(?=[\s>/]))
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


def site_lang(config) -> str:
    lang = (_config_get(config, "extra") or {}).get("site_lang")
    if lang in LANGS:
        return lang
    site_dir = str(_config_get(config, "site_dir") or "").replace("\\", "/")
    for code in ("en", "vi"):
        if site_dir.rstrip("/").endswith(f"/{code}"):
            return code
    return "zh"


def site_root_url(config) -> str:
    url = str(_config_get(config, "site_url") or "").rstrip("/")
    prefix = _PREFIX[site_lang(config)]
    if prefix and url.endswith(f"/{prefix}"):
        url = url[: -len(prefix) - 1]
    return url


def _docs_root(config) -> Path:
    return Path(str(_config_get(config, "docs_dir") or "docs")).parent


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
    return sop._markdown_exists(_docs_root(config) / _DOCS[lang], rel)


def is_stub(config, rel: str) -> bool:
    rel = rel.strip("/")
    key = f"{rel}.md" if rel else "index.md"
    return vi_status(config).get(key) == STUB


def _real_alternate(config, lang: str, rel: str) -> bool:
    """A counterpart worth announcing to search engines."""
    if not has_page(config, lang, rel):
        return False
    return not (lang == "vi" and is_stub(config, rel))


def rewrite(output: str, rel: str, config) -> str:
    here_lang = site_lang(config)
    here = abs_path(rel, here_lang)
    origin = site_root_url(config)
    handled = LANGS if here_lang == "vi" else ("vi",)
    here_is_stub = here_lang == "vi" and is_stub(config, rel)

    def repl(match):
        lang = match.group("lang").lower()
        if match.group("tag").lower() == "link":
            href = origin + abs_path(rel, lang)
        else:
            target = rel if has_page(config, lang, rel) else ""
            href = sop._relative_href(here, abs_path(target, lang))
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    output = _tag_href(handled).sub(repl, output)
    for lang in handled:
        if here_is_stub or not _real_alternate(config, lang, rel):
            output = _link_tag(lang).sub("", output)

    if here_lang == "vi":
        snippets = []
        if not here_is_stub and has_page(config, "zh", rel):
            if not re.search(r"hreflang=[\"']?x-default", output, re.IGNORECASE):
                href = origin + abs_path(rel, "zh")
                snippets.append(
                    f'<link rel="alternate" hreflang="x-default" href="{href}">'
                )
        if "XMLHttpRequest.prototype.open" not in output:
            snippets.append(sop._XHR_PATCH)
        output = sop._inject_head(output, snippets)
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
        rel = loc[len(base) :] if loc.startswith(base) else ""
        if loc.startswith(base) and is_stub(config, rel):
            root.remove(url_el)
    return ET.tostring(root, encoding="utf-8", xml_declaration=True).decode("utf-8")


def on_post_build(config):
    if site_lang(config) != "vi":
        return
    sitemap = Path(str(_config_get(config, "site_dir") or "")) / "sitemap.xml"
    if not sitemap.is_file():
        return
    text = drop_stubs_from_sitemap(sitemap.read_text(encoding="utf-8"), config)
    sitemap.write_text(text, encoding="utf-8")
    gz = sitemap.with_name("sitemap.xml.gz")
    if gz.exists():
        gz.write_bytes(gzip.compress(text.encode("utf-8"), mtime=0))
