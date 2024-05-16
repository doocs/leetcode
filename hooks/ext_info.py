import re

from mkdocs import plugins


# https://www.mkdocs.org/dev-guide/plugins/#events


@plugins.event_priority(100)
def on_page_markdown(markdown, page, config, files):
    difficulty = page.meta.get("difficulty")
    rating = page.meta.get("rating")
    if not difficulty and not rating:
        return markdown
    source = page.meta.get("source")
    is_cn_problem_page = (page.edit_url or "").endswith("README.md")
    images = []
    if source:
        title = "来源" if is_cn_problem_page else "Source"
        images.append(
            f'<img src="https://img.shields.io/badge/{title}-{source}-4051B5?style=flat-square">'
        )
    if difficulty:
        title = "难度" if is_cn_problem_page else "Difficulty"
        images.append(
            f'<img src="https://img.shields.io/badge/{title}-{difficulty}-4051B5?style=flat-square">'
        )
    if rating:
        title = "分数" if is_cn_problem_page else "Rating"
        images.append(
            f'<img src="https://img.shields.io/badge/{title}-{rating}-4051B5?style=flat-square">'
        )
    # 将 images 放到 <p> 标签中
    images_html = "<p>" + " ".join(images) + "</p>"
    heading_pattern = re.compile(r"(^# .+?$)", re.MULTILINE)
    match = heading_pattern.search(markdown)
    if match:
        insert_position = match.end()
        markdown = (
            markdown[:insert_position]
            + "\n\n"
            + images_html
            + "\n\n"
            + markdown[insert_position:]
        )

    return markdown
