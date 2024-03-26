# https://www.mkdocs.org/dev-guide/plugins/#events


def on_page_markdown(markdown, page, config, files):
    page_edit_url = page.meta.get("edit_url")
    if page_edit_url:
        print(dir(page))
    page.edit_url = str(page.meta.get("edit_url")) if page_edit_url else None
