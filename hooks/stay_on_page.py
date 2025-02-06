import re


def on_post_page(output, page, config):
    """
    <ul class="md-select__list">

          <li class="md-select__item">
            <a href="/leetcode/en/" hreflang="en" class="md-select__link">
              English
            </a>
          </li>

          <li class="md-select__item">
            <a href="/leetcode/" hreflang="zh" class="md-select__link">
              中文
            </a>
          </li>

      </ul>
    """
    
    url = page.abs_url
    cn_url = url.replace("/leetcode/en/", "/leetcode/")
    en_url = cn_url.replace("/leetcode/", "/leetcode/en/")
    support_en_lang = not cn_url.startswith("/leetcode/lcof") and not cn_url.startswith(
        "/leetcode/lcof2"
    )
    patterns = {
        "zh": r'(<a\s+[^>]*href=["\'])[^"\']*(["\'][^>]*hreflang=["\']zh["\'][^>]*>)',
        "en": r'(<a\s+[^>]*href=["\'])[^"\']*(["\'][^>]*hreflang=["\']en["\'][^>]*>)',
    }
    output = re.sub(patterns["zh"], r"\1" + cn_url + r"\2", output)
    if support_en_lang:
        output = re.sub(patterns["en"], r"\1" + en_url + r"\2", output)
    return output
