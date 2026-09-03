import re

# Minify may strip quotes: <a href=/en/ hreflang=en>
_HREFLANG_HREF = re.compile(
    r"""
    (?P<prefix>
        <a\b
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


def on_post_page(output, page, config):
    if not output:
        return output

    rel = _page_rel(page)
    cn_url = f"/{rel}" if rel else "/"
    en_url = f"/en/{rel}" if rel else "/en/"
    prefix = rel.split("/", 1)[0] if rel else ""
    support_en = prefix not in ("lcof", "lcof2")

    def repl(match):
        lang = match.group("lang").lower()
        if lang == "en" and not support_en:
            return match.group(0)
        href = en_url if lang == "en" else cn_url
        return f"{match.group('prefix')}{href}{match.group('suffix')}"

    try:
        return _HREFLANG_HREF.sub(repl, output)
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
