import re

from mkdocs import plugins


# https://www.mkdocs.org/dev-guide/plugins/#events


@plugins.event_priority(90)
def on_page_markdown(markdown, page, config, files):
    pattern = r"<pre>\s*.*?<\/pre>"
    markdown = re.sub(
        pattern,
        lambda match: re.sub(r"<code>|</code>", "", match.group(0)),
        markdown,
        flags=re.DOTALL | re.IGNORECASE,
    )
    return markdown
