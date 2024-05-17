from mkdocs import plugins


# https://www.mkdocs.org/dev-guide/plugins/#events


@plugins.event_priority(80)
def on_page_markdown(markdown, page, config, files):
    page_edit_url = page.meta.get("edit_url")
    page.edit_url = str(page_edit_url) if page_edit_url else None
