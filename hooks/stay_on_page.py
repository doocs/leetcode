import re

_HREFLANG_HREF = re.compile(
    r'(<a\b(?=[^>]*\bhreflang="(?P<lang>zh|en)")[^>]*\bhref=")[^"]*(")',
    re.IGNORECASE,
)


def on_post_page(output, page, config):
    if not output:
        return output

    rel = (page.url or "").lstrip("/")
    cn_url = f"/{rel}" if rel else "/"
    en_url = f"/en/{rel}" if rel else "/en/"
    prefix = rel.split("/", 1)[0] if rel else ""
    support_en = prefix not in ("lcof", "lcof2")

    def repl(match):
        lang = match.group("lang").lower()
        href = en_url if lang == "en" and support_en else cn_url
        if lang == "en" and not support_en:
            return match.group(0)
        return f"{match.group(1)}{href}{match.group(3)}"

    try:
        return _HREFLANG_HREF.sub(repl, output)
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
