"""Keep the first paint from waiting on lightbox, MathJax, and analytics."""

from __future__ import annotations

import re

# These scripts are safe to run after the document is parsed. MathJax config
# stays ahead of the library because deferred scripts keep document order.
_DEFER_SRC = re.compile(
    r"<script\b[^>]*\bsrc=(['\"]?)[^'\"\s>]*"
    r"(?:glightbox\.min\.js|mathjax\.js|tex-mml-chtml\.js)\1[^>]*>",
    re.IGNORECASE,
)
_INIT = re.compile(
    r"(<script\b[^>]*\bid=(['\"]?)init-glightbox\2[^>]*>)(.*?)(</script>)",
    re.IGNORECASE | re.DOTALL,
)
_ANALYTICS_NOW = re.compile(
    r"<script>\s*"
    r'(?:"undefined"!=typeof __md_analytics&&__md_analytics\(\)'
    r"|if\s*\(\s*typeof __md_analytics\s*!==\s*[\"']undefined[\"']\s*\)\s*"
    r"__md_analytics\(\)\s*)"
    r"\s*</script>",
    re.IGNORECASE,
)
_ANALYTICS_LATER = (
    '<script>"undefined"!=typeof __md_analytics&&'
    'window.addEventListener("load",__md_analytics)</script>'
)


def _defer(match: re.Match) -> str:
    tag = match.group(0)
    if re.search(r"\bdefer\b", tag, re.IGNORECASE):
        return tag
    return tag[:-1] + " defer>"


def _wrap_init(match: re.Match) -> str:
    body = match.group(3)
    if "DOMContentLoaded" in body:
        return match.group(0)
    return (
        match.group(1)
        + 'document.addEventListener("DOMContentLoaded",function(){'
        + body
        + "});"
        + match.group(4)
    )


def on_post_page(output, page, config):
    if not output:
        return output
    output = _DEFER_SRC.sub(_defer, output)
    output = _INIT.sub(_wrap_init, output)
    return _ANALYTICS_NOW.sub(_ANALYTICS_LATER, output)
