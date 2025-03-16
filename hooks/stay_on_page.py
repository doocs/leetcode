import re


def on_post_page(output, page, config):
    """
    <ul class="md-select__list">

          <li class="md-select__item">
            <a href="/en/" hreflang="en" class="md-select__link">
              English
            </a>
          </li>

          <li class="md-select__item">
            <a href="/" hreflang="zh" class="md-select__link">
              中文
            </a>
          </li>

      </ul>
    """

    url = page.abs_url
    cn_url = url.replace("/en/", "/")
    en_url = "/en" + cn_url
    support_en_lang = not cn_url.startswith("/lcof") and not cn_url.startswith("/lcof2")
    patterns = {
        "zh": r'(<a\s+[^>]*href=["\'])[^"\']*(["\'][^>]*hreflang=["\']zh["\'][^>]*>)',
        "en": r'(<a\s+[^>]*href=["\'])[^"\']*(["\'][^>]*hreflang=["\']en["\'][^>]*>)',
    }
    output = re.sub(patterns["zh"], r"\1" + cn_url + r"\2", output)
    if support_en_lang:
        output = re.sub(patterns["en"], r"\1" + en_url + r"\2", output)
    return output
