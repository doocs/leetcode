import re

# pymdownx.arithmatex wraps formulas in class="arithmatex".
# The MathJax config script also contains the string "arithmatex", so
# only look at markup classes — not a raw substring search.
_ARITHMATEX = re.compile(
    r"""<[a-z][^>]*\bclass=(['\"]?)[^'\">]*\barithmatex\b""",
    re.IGNORECASE,
)
_MATHJAX_SCRIPT = re.compile(
    r"""<script\b(?=[^>]*\bsrc=(['\"]?)[^'\">]*"""
    r"""(?:/mathjax\.js|tex-mml-chtml\.js))[^>]*>\s*</script>""",
    re.IGNORECASE,
)


def on_post_page(output, page, config):
    if not output or _ARITHMATEX.search(output):
        return output
    return _MATHJAX_SCRIPT.sub("", output)
