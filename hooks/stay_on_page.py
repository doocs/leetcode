import re
from bs4 import BeautifulSoup


def on_post_page(output, page, config):
    try:
        if not output:
            return output

        soup = BeautifulSoup(output, "html.parser")

        url = page.abs_url
        if not url:
            return output

        cn_url = url.replace("/en/", "/")
        en_url = "/en" + cn_url
        support_en_lang = not cn_url.startswith("/lcof") and not cn_url.startswith("/lcof2")

        # Update Chinese link
        zh_link = soup.find("a", attrs={"hreflang": "zh"})
        if zh_link:
            zh_link["href"] = cn_url

        # Update English link
        if support_en_lang:
            en_link = soup.find("a", attrs={"hreflang": "en"})
            if en_link:
                en_link["href"] = en_url

        return str(soup)
    except Exception as e:
        print(f"Error in stay_on_page hook: {e}")
        return output
