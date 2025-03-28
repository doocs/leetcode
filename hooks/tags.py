import re
from bs4 import BeautifulSoup


def on_post_page(output, page, config):
    is_cn_tag_page = page.abs_url == "/tags/"
    is_en_tag_page = page.abs_url == "/en/tags/"
    if not is_cn_tag_page and not is_en_tag_page:
        return output
    soup = BeautifulSoup(output, "html.parser")
    tags = soup.find_all("span", class_="md-tag")
    for tag in tags:
        tag_li = tag.find_next("ul").find_all("li")
        tag_li.sort(key=lambda x: int(re.search(r"\d+", x.a.text).group()))
        tag.find_next("ul").clear()
        for li in tag_li:
            tag.find_next("ul").append(li)
    return str(soup)