"""Turn <!-- thinking --> blockquotes into a Material admonition."""

import re

BLOCK_RE = re.compile(
    r"<!-- thinking:start -->\s*(.*?)<!-- thinking:end -->",
    re.DOTALL,
)


def _strip_quote(body: str) -> tuple[str, str]:
    lines = body.strip().splitlines()
    title = "思考"
    content: list[str] = []
    for line in lines:
        if line.startswith("> "):
            raw = line[2:]
        elif line.strip() == ">":
            raw = ""
        else:
            raw = line
        if not content and raw.strip() in ("**思考**", "**Thinking**"):
            title = "思考" if "思考" in raw else "Thinking"
            continue
        content.append(raw)
    while content and not content[0].strip():
        content.pop(0)
    while content and not content[-1].strip():
        content.pop()
    return title, "\n".join(content)


def convert_thinking_blocks(markdown: str) -> str:
    def repl(match: re.Match[str]) -> str:
        title, body = _strip_quote(match.group(1))
        indented = "\n".join(
            f"    {line}" if line.strip() else "" for line in body.splitlines()
        )
        return f'!!! thinking "{title}"\n\n{indented}\n'

    return BLOCK_RE.sub(repl, markdown)


def on_page_markdown(markdown, page, config, files):
    return convert_thinking_blocks(markdown)
