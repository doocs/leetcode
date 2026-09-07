import re
from pathlib import Path
from posixpath import dirname, relpath
from urllib.parse import urlparse
from xml.etree import ElementTree as ET

# Minify may strip quotes: <a href=/en/ hreflang=en>
# <a hreflang> stays page-relative so Gitee /leetcode/ hosting works.
# <link rel=alternate> is the current page pair for SEO. Material's
# setupAlternate then requests sitemap.xml against that href
# (mkdocs-material#6582, #7352); rewrite_sitemap_pathname maps those
# requests back to the language-root sitemaps.
_HREFLANG_HREF = re.compile(
    r"""
    (?P<prefix>
        <(?P<tag>a|link)\b
        (?=[^>]*\bhreflang=(?P<lq>["']?)(?P<lang>zh|en)(?P=lq)(?=[\s>]))
        [^>]*\bhref=(?P<hq>["']?)
    )
    [^"'\s>]*
    (?P<suffix>(?P=hq))
    """,
    re.IGNORECASE | re.VERBOSE,
)
_LINK_HREFLANG_EN = re.compile(
    r"<link\b(?=[^>]*\bhreflang=(['\"]?)en\1)[^>]*>",
    re.IGNORECASE,
)
_HEAD_END = re.compile(r"</head>", re.IGNORECASE)
_SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9"
_XHTML_NS = "http://www.w3.org/1999/xhtml"
_CN_ONLY = frozenset(("lcof", "lcof2"))
_SERIES = frozenset(
    ("lc", "lcof", "lcof2", "lcci", "lcp", "lcs", "contest", "tags")
)

# Keep in sync with rewrite_sitemap_pathname().
_XHR_PATCH = (
    "<script>"
    "!function(){"
    "function rewrite(p){"
    "if(!/sitemap\\.xml$/i.test(p))return p;"
    "var parts=p.split('/').filter(function(s){return s&&!/^sitemap\\.xml$/i.test(s)});"
    "var series={lc:1,lcof:1,lcof2:1,lcci:1,lcp:1,lcs:1,contest:1,tags:1};"
    "var en=-1;"
    "for(var i=0;i<parts.length;i++)if(parts[i].toLowerCase()==='en'){en=i;break}"
    "if(en>=0)return'/'+parts.slice(0,en+1).join('/')+'/sitemap.xml';"
    "if(parts[0]&&series[parts[0]])return'/sitemap.xml';"
    "if(parts.length>=2&&series[parts[1]])return'/'+parts[0]+'/sitemap.xml';"
    "if(parts.length===1)return'/'+parts[0]+'/sitemap.xml';"
    "return'/sitemap.xml'"
    "}"
    "var n=XMLHttpRequest.prototype.open;"
    "XMLHttpRequest.prototype.open=function(m,u){"
    "var a=arguments;"
    "try{"
    "var x=new URL(String(u),location.href),r=rewrite(x.pathname);"
    "if(r!==x.pathname){x.pathname=r;a=Array.prototype.slice.call(arguments);a[1]=x.href}"
    "}catch(e){}"
    "return n.apply(this,a)"
    "}"
    "}()"
    "</script>"
)


def rewrite_sitemap_pathname(pathname: str) -> str:
    raw = pathname or ""
    if not raw.lower().endswith("sitemap.xml"):
        return pathname
    parts = [p for p in raw.split("/") if p and p.lower() != "sitemap.xml"]
    en_idx = next((i for i, p in enumerate(parts) if p.lower() == "en"), -1)
    if en_idx >= 0:
        return "/" + "/".join(parts[: en_idx + 1]) + "/sitemap.xml"
    if parts and parts[0] in _SERIES:
        return "/sitemap.xml"
    if len(parts) >= 2 and parts[1] in _SERIES:
        return f"/{parts[0]}/sitemap.xml"
    if len(parts) == 1:
        return f"/{parts[0]}/sitemap.xml"
    return "/sitemap.xml"


def _config_get(config, key: str, default=""):
    if isinstance(config, dict):
        return config.get(key, default)
    return getattr(config, key, default)


def _page_rel(page) -> str:
    url = (getattr(page, "url", None) or "").strip().lstrip("/")
    if url in ("", "./", "index.html", "index.htm"):
        return ""
    return url


def _is_en_site(config) -> bool:
    site_url = str(_config_get(config, "site_url") or "").rstrip("/")
    site_dir = str(_config_get(config, "site_dir") or "").replace("\\", "/").rstrip("/")
    return site_url.endswith("/en") or site_dir.endswith("/en") or site_dir == "en"


def _site_origin(config) -> str:
    site_url = str(_config_get(config, "site_url") or "").rstrip("/")
    if site_url.endswith("/en"):
        site_url = site_url[:-3]
    return site_url


def _site_dir(config) -> str:
    return str(_config_get(config, "site_dir") or "")


def _docs_dir(config) -> Path:
    raw = str(_config_get(config, "docs_dir") or "")
    return Path(raw) if raw else Path()


def _peer_docs_dir(config):
    docs = _docs_dir(config)
    if not docs.name:
        return None
    if docs.name == "docs-en" or _is_en_site(config):
        peer = docs.parent / "docs"
    else:
        peer = docs.parent / "docs-en"
    try:
        return peer if peer.is_dir() else None
    except OSError:
        return None


def _markdown_exists(docs_dir: Path, rel: str) -> bool:
    rel = (rel or "").strip("/")
    paths = (
        [docs_dir / "index.md"]
        if not rel
        else [docs_dir / f"{rel}.md", docs_dir / rel / "index.md"]
    )
    return any(p.is_file() for p in paths)


def _abs_url(rel: str, *, en: bool) -> str:
    if en:
        return f"/en/{rel}" if rel else "/en/"
    return f"/{rel}" if rel else "/"


def _relative_href(from_abs: str, to_abs: str) -> str:
    to_is_dir = to_abs.endswith("/")
    if from_abs.endswith("/"):
        start = from_abs.rstrip("/") or "/"
    else:
        start = dirname(from_abs) or "/"
    target = to_abs.rstrip("/") if to_is_dir else to_abs
    if target == "":
        target = "/"
    rel = relpath(target, start)
    if to_is_dir:
        if rel in (".", ""):
            return "./"
        return rel if rel.endswith("/") else f"{rel}/"
    if rel in (".", ""):
        return to_abs.rsplit("/", 1)[-1] or "./"
    return rel


def _prefix_supports_en(rel: str) -> bool:
    prefix = rel.split("/", 1)[0] if rel else ""
    return prefix not in _CN_ONLY


def _has_en_page(rel: str, config) -> bool:
    if not _prefix_supports_en(rel):
        return False
    if isinstance(config, dict) and "has_en" in config:
        return bool(config["has_en"])
    if _is_en_site(config):
        return True
    peer = _peer_docs_dir(config)
    if peer is None:
        return True
    return _markdown_exists(peer, rel)


def _page_href(rel: str, *, en: bool, origin: str) -> str:
    path = _abs_url(rel, en=en)
    return origin + path if origin else path


def _hreflang_pair(rel: str, origin: str, *, has_en: bool) -> list:
    cn = _page_href(rel, en=False, origin=origin)
    links = [("zh", cn), ("x-default", cn)]
    if has_en:
        links.insert(1, ("en", _page_href(rel, en=True, origin=origin)))
    return links


def _sitemap_href(config) -> str:
    origin = _site_origin(config)
    if not origin:
        return "/en/sitemap.xml" if _is_en_site(config) else "/sitemap.xml"
    if _is_en_site(config):
        return f"{origin}/en/sitemap.xml"
    return f"{origin}/sitemap.xml"


def _inject_head(output: str, snippets: list) -> str:
    extra = "".join(s for s in snippets if s)
    if not extra:
        return output
    return _HEAD_END.sub(f"{extra}</head>", output, count=1)


def _rel_from_loc(loc: str) -> str:
    path = urlparse(loc.strip()).path
    trailing = path.endswith("/")
    path = path.strip("/")
    if path == "en" or path.startswith("en/"):
        path = path[3:].lstrip("/")
    if path and trailing:
        return f"{path}/"
    return path


def _annotate_sitemap_url(url_el, origin: str, has_en) -> None:
    loc_el = url_el.find(f"{{{_SITEMAP_NS}}}loc")
    if loc_el is None or not (loc_el.text or "").strip():
        return
    for child in list(url_el):
        if child.tag == f"{{{_XHTML_NS}}}link":
            url_el.remove(child)
    rel = _rel_from_loc(loc_el.text)
    for lang, href in _hreflang_pair(rel, origin, has_en=has_en(rel)):
        el = ET.SubElement(url_el, f"{{{_XHTML_NS}}}link")
        el.set("rel", "alternate")
        el.set("hreflang", lang)
        el.set("href", href)


def annotate_sitemap(xml_text: str, origin: str, has_en=None) -> str:
    ET.register_namespace("", _SITEMAP_NS)
    ET.register_namespace("xhtml", _XHTML_NS)
    if has_en is None:
        has_en = _prefix_supports_en
    root = ET.fromstring(xml_text)
    for url_el in root.findall(f"{{{_SITEMAP_NS}}}url"):
        _annotate_sitemap_url(url_el, origin, has_en)
    return ET.tostring(root, encoding="utf-8", xml_declaration=True).decode("utf-8")


def on_post_build(config):
    sitemap = Path(_site_dir(config)) / "sitemap.xml"
    if not sitemap.is_file():
        return
    sitemap.write_text(
        annotate_sitemap(
            sitemap.read_text(encoding="utf-8"),
            _site_origin(config),
            has_en=lambda rel: _has_en_page(rel, config),
        ),
        encoding="utf-8",
    )


def on_post_page(output, page, config):
    if not output:
        return output

    rel = _page_rel(page)
    here = _abs_url(rel, en=_is_en_site(config))
    support_en = _has_en_page(rel, config)
    origin = _site_origin(config)
    cn_href = _page_href(rel, en=False, origin=origin)
    en_href = _page_href(rel, en=True, origin=origin)
    cn_a = _relative_href(here, _abs_url(rel, en=False))
    en_target = _abs_url(rel, en=True) if support_en else _abs_url("", en=True)
    en_a = _relative_href(here, en_target)

    def repl(match):
        en = match.group("lang").lower() == "en"
        if match.group("tag").lower() == "link":
            href = en_href if en else cn_href
        else:
            href = en_a if en else cn_a
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    try:
        output = _HREFLANG_HREF.sub(repl, output)
        if not support_en:
            output = _LINK_HREFLANG_EN.sub("", output)
        snippets = []
        if not re.search(r'hreflang=["\']?x-default', output, re.IGNORECASE):
            snippets.append(
                f'<link rel="alternate" hreflang="x-default" href="{cn_href}">'
            )
        if not re.search(r'rel=["\']?sitemap["\']?', output, re.IGNORECASE):
            snippets.append(
                '<link rel="sitemap" type="application/xml" title="Sitemap" '
                f'href="{_sitemap_href(config)}">'
            )
        if "XMLHttpRequest.prototype.open" not in output:
            snippets.append(_XHR_PATCH)
        return _inject_head(output, snippets)
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
