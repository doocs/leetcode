import re
from posixpath import relpath

# Minify may strip quotes: <a href=/en/ hreflang=en>
# Also rewrite <link rel=alternate href=/en/ hreflang=en>
_HREFLANG_HREF = re.compile(
    r"""
    (?P<prefix>
        <(?:a|link)\b
        (?=[^>]*\bhreflang=(?P<lq>["']?)(?P<lang>zh|en)(?P=lq)(?=[\s>]))
        [^>]*\bhref=(?P<hq>["']?)
    )
    [^"'\s>]*
    (?P<suffix>(?P=hq))
    """,
    re.IGNORECASE | re.VERBOSE,
)


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


def _abs_url(rel: str, *, en: bool) -> str:
    if en:
        return f"/en/{rel}" if rel else "/en/"
    return f"/{rel}" if rel else "/"


def _relative_href(from_abs: str, to_abs: str) -> str:
    from_dir = from_abs if from_abs.endswith("/") else from_abs + "/"
    to_dir = to_abs if to_abs.endswith("/") else to_abs + "/"
    rel = relpath(to_dir, from_dir)
    if rel in (".", ""):
        return "./"
    return rel if rel.endswith("/") else f"{rel}/"


def on_post_page(output, page, config):
    if not output:
        return output

    rel = _page_rel(page)
    here = _abs_url(rel, en=_is_en_site(config))
    prefix = rel.split("/", 1)[0] if rel else ""
    support_en = prefix not in ("lcof", "lcof2")
    cn_url = _relative_href(here, _abs_url(rel, en=False))
    en_target = _abs_url(rel, en=True) if support_en else _abs_url("", en=True)
    en_url = _relative_href(here, en_target)

    def repl(match):
        lang = match.group("lang").lower()
        href = en_url if lang == "en" else cn_url
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    try:
        return _HREFLANG_HREF.sub(repl, output)
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
