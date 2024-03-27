from bs4 import BeautifulSoup


def on_page_content(html, page, config, files):
    # 修改 tags 页每个 tag 的 id 属性值
    is_cn_tag_page = page.abs_url == "/leetcode/tags/"
    if is_cn_tag_page:
        soup = BeautifulSoup(html, "html.parser")
        h2_tags = soup.find_all("h2")
        for tag in h2_tags:
            span_tag = tag.find("span", class_="md-tag")
            if span_tag:
                tag["id"] = span_tag.text.strip()
        # 重新排序每个 tag 下的问题列表
        ul_tags = soup.find_all("ul")
        for ul_tag in ul_tags:
            li_tags = ul_tag.find_all("li")
            li_tags.sort(key=lambda x: int(x.text.split(".")[0].strip()))
            ul_tag.clear()
            for li_tag in li_tags:
                ul_tag.append(li_tag)
        html = str(soup)

    return html


def on_page_context(context, page, config, nav):
    # 修改每个问题顶部 tags 的跳转链接
    is_cn_problem_page = (page.edit_url or "").endswith("README.md")
    has_tag = "tags" in context
    if is_cn_problem_page and has_tag:
        for tag in context["tags"]:
            tag["url"] = f'tags/#{tag["name"]}'
    return context


def on_post_page(output, page, config):
    # 修改 tags 页的左侧导航链接
    is_cn_tag_page = page.abs_url == "/leetcode/tags/"
    if is_cn_tag_page:
        soup = BeautifulSoup(output, "html.parser")
        nav_tag = soup.find("nav", class_="md-nav md-nav--secondary")
        if nav_tag:
            ul_tag = nav_tag.find("ul", class_="md-nav__list")
            if ul_tag:
                for li_tag in ul_tag.find_all("li", class_="md-nav__item"):
                    a_tag = li_tag.find("a", class_="md-nav__link")
                    if a_tag:
                        span_tag = a_tag.find("span", class_="md-ellipsis")
                        if span_tag:
                            tag_name = span_tag.text.strip()
                            a_tag["href"] = f"#{tag_name}"
        output = str(soup)
    return output
