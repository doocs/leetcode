from mkdocs import plugins

from bs4 import BeautifulSoup


# https://www.mkdocs.org/dev-guide/plugins/#events

colors = {
    "简单": "#46c6c2",
    "中等": "#fac31d",
    "困难": "#f8615c",
    "Easy": "#46c6c2",
    "Medium": "#fac31d",
    "Hard": "#f8615c",
}


@plugins.event_priority(90)
def on_post_page(output, page, config):
    is_cn_problem_page = (page.edit_url or "").endswith("README.md")
    difficulty = page.meta.get("difficulty")
    rating = page.meta.get("rating")
    if not difficulty and not rating:
        return output
    if rating:
        rating = f"难度分 {rating}" if is_cn_problem_page else f"Rating {rating}"
    html = str(output)
    soup = BeautifulSoup(html, "html.parser")
    div = soup.new_tag("div", attrs={"class": "rating"})
    soup.select_one("h1").insert_after(div)
    color = colors.get(difficulty) or "black"
    for s in (difficulty, rating):
        if not s:
            continue
        sub = soup.new_tag("div", attrs={"class": "rating-item"})
        sub.string = str(s)
        sub["style"] = f"color: {color};"
        div.append(sub)
    return str(soup)
