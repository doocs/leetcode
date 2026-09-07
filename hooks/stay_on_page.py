import re

# Minify may strip quotes: <a href=/en/ hreflang=en>
# Also rewrite <link rel=alternate href=/en/ hreflang=en>
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
_HEAD_END = re.compile(r"</head>", re.IGNORECASE)


def _page_rel(page) -> str:
    url = (getattr(page, "url", None) or "").strip().lstrip("/")
    if url in ("", "./", "index.html", "index.htm"):
        return ""
    return url


def _is_en_site(config) -> bool:
    if isinstance(config, dict):
        site_url = str(config.get("site_url") or "")
        site_dir = str(config.get("site_dir") or "")
    else:
        site_url = str(getattr(config, "site_url", "") or "")
        site_dir = str(getattr(config, "site_dir", "") or "")
    site_url = site_url.rstrip("/")
    site_dir = site_dir.replace("\\", "/").rstrip("/")
    return site_url.endswith("/en") or site_dir.endswith("/en") or site_dir == "en"


def _site_origin(config) -> str:
    if isinstance(config, dict):
        site_url = str(config.get("site_url") or "")
    else:
        site_url = str(getattr(config, "site_url", "") or "")
    site_url = site_url.rstrip("/")
    if site_url.endswith("/en"):
        site_url = site_url[:-3]
    return site_url


def _abs_url(rel: str, *, en: bool) -> str:
    if en:
        return f"/en/{rel}" if rel else "/en/"
    return f"/{rel}" if rel else "/"


def _hreflang_href(path: str, *, tag: str, origin: str) -> str:
    # <link rel=alternate> must be absolute so Material/clients do not resolve
    # sitemap.xml against the current page (mkdocs-material#6582, #7352).
    # <a> stays root-absolute so the language switcher keeps the same page.
    if tag.lower() == "link" and origin:
        return origin + path
    return path


def _sitemap_href(config) -> str:
    origin = _site_origin(config)
    if not origin:
        return "/en/sitemap.xml" if _is_en_site(config) else "/sitemap.xml"
    if _is_en_site(config):
        return f"{origin}/en/sitemap.xml"
    return f"{origin}/sitemap.xml"


def _inject_sitemap_link(output: str, href: str) -> str:
    if re.search(r'rel=["\']?sitemap["\']?', output, re.IGNORECASE):
        return output
    link = f'<link rel="sitemap" type="application/xml" title="Sitemap" href="{href}">'
    return _HEAD_END.sub(f"{link}</head>", output, count=1)


def on_post_page(output, page, config):
    if not output:
        return output

    rel = _page_rel(page)
    prefix = rel.split("/", 1)[0] if rel else ""
    support_en = prefix not in ("lcof", "lcof2")
    origin = _site_origin(config)
    cn_path = _abs_url(rel, en=False)
    en_path = _abs_url(rel, en=True) if support_en else _abs_url("", en=True)

    def repl(match):
        lang = match.group("lang").lower()
        path = en_path if lang == "en" else cn_path
        href = _hreflang_href(path, tag=match.group("tag"), origin=origin)
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    try:
        output = _HREFLANG_HREF.sub(repl, output)
        return _inject_sitemap_link(output, _sitemap_href(config))
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
