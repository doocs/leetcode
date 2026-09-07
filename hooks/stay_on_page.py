import re
from posixpath import dirname, relpath

# Minify may strip quotes: <a href=/en/ hreflang=en>
# <a hreflang> stays page-relative so Gitee /leetcode/ hosting works.
# <link rel=alternate> stays on the language root so clients resolve
# sitemap.xml to /sitemap.xml or /en/sitemap.xml (mkdocs-material#6582, #7352).
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


def _language_root(*, en: bool) -> str:
    return "/en/" if en else "/"


def _alternate_href(*, en: bool, origin: str) -> str:
    root = _language_root(en=en)
    if origin:
        return origin + root
    return root


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
    here = _abs_url(rel, en=_is_en_site(config))
    prefix = rel.split("/", 1)[0] if rel else ""
    support_en = prefix not in ("lcof", "lcof2")
    origin = _site_origin(config)
    cn_a = _relative_href(here, _abs_url(rel, en=False))
    en_target = _abs_url(rel, en=True) if support_en else _abs_url("", en=True)
    en_a = _relative_href(here, en_target)

    def repl(match):
        en = match.group("lang").lower() == "en"
        if match.group("tag").lower() == "link":
            href = _alternate_href(en=en, origin=origin)
        else:
            href = en_a if en else cn_a
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    try:
        output = _HREFLANG_HREF.sub(repl, output)
        return _inject_sitemap_link(output, _sitemap_href(config))
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
