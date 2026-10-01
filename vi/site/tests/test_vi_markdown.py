import sys
import unittest
from pathlib import Path
from types import SimpleNamespace

sys.path.insert(0, str(Path(__file__).resolve().parent))
from helpers import use_hooks  # noqa: E402

use_hooks()
import fork_site  # noqa: E402
import vi_markdown  # noqa: E402

DOC = """# [1. Two Sum](https://leetcode.com/problems/two-sum)

[中文文档](/solution/0000-0099/0001.Two%20Sum/README.md)

## Lời giải

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Dòng một.
>
> Dòng hai.

<!-- thinking:end -->

Xem [bài 2](/solution/0000-0099/0002.Add%20Two%20Numbers/README_EN.md).

<!-- tabs:start -->

#### Python3

```python
print(1)
```

<!-- tabs:end -->
"""


def page(meta=None, url="lc/1/"):
    return SimpleNamespace(meta=dict(meta or {}), url=url, edit_url=None)


class ViMarkdownTest(unittest.TestCase):
    def test_badges_use_vietnamese_labels(self):
        p = page(
            {"difficulty": "Easy", "source": "Weekly Contest 1 Q1", "rating": 1200}
        )
        out = vi_markdown.add_difficulty_info("# T\n\nbody\n", p)
        self.assertIn('lc-badge__label">Nguồn<', out)
        self.assertIn('lc-badge__label">Độ khó<', out)
        self.assertIn('lc-badge__value">Dễ<', out)
        self.assertIn('lc-badge__label">Điểm<', out)
        self.assertLess(out.index("# T"), out.index("lc-badges"))

    def test_unknown_difficulty_kept(self):
        out = vi_markdown.add_difficulty_info("# T\n", page({"difficulty": "Unknown"}))
        self.assertIn('lc-badge__value">Unknown<', out)

    def test_no_badges_without_meta(self):
        self.assertEqual(vi_markdown.add_difficulty_info("# T\n", page()), "# T\n")

    def test_thinking_block(self):
        out = vi_markdown.convert_thinking_blocks(DOC)
        self.assertIn('!!! thinking "Tư duy"\n\n    Dòng một.\n\n    Dòng hai.\n', out)
        self.assertNotIn("**Tư duy**", out)
        self.assertNotIn("thinking:start", out)

    def test_page_markdown_pipeline(self):
        out = vi_markdown.on_page_markdown(DOC, page({"difficulty": "Easy"}), {}, None)
        self.assertNotIn("中文文档", out)
        self.assertIn("(2.md)", out)
        self.assertIn('=== "Python3"', out)
        self.assertIn('!!! thinking "Tư duy"', out)
        self.assertIn('lc-badge__value">Dễ<', out)

    def test_outdated_notice(self):
        p = page({"vi_status": "outdated", "difficulty": "Easy"}, url="lcci/1.1/")
        config = {"extra": {"upstream_site": "https://up.example"}}
        out = vi_markdown.on_page_markdown(DOC, p, config, None)
        self.assertIn('!!! warning "Bản dịch có thể đã cũ"', out)
        self.assertIn("[bản English](https://up.example/en/lcci/1.1/)", out)
        out = vi_markdown.on_page_markdown(DOC, p, {}, None)
        self.assertIn("(https://leetcode.doocs.org/en/lcci/1.1/)", out)
        self.assertLess(out.index("lc-badges"), out.index("!!! warning"))
        fresh = vi_markdown.on_page_markdown(DOC, page({}), {}, None)
        self.assertNotIn("!!! warning", fresh)

    def test_tags_page_is_sorted_by_number(self):
        html = (
            '<html><body><span class="md-tag">Array</span><ul>'
            '<li><a href="x">10. B</a></li><li><a href="y">2. A</a></li>'
            "</ul></body></html>"
        )
        out = vi_markdown.on_post_page(html, page(url="tags/"), {})
        self.assertLess(out.index("2. A"), out.index("10. B"))

    def test_stub_is_noindex(self):
        html = "<html><head></head><body></body></html>"
        out = vi_markdown.on_post_page(html, page({"vi_status": "stub"}), {})
        self.assertIn(vi_markdown.NOINDEX + "</head>", out)
        out = vi_markdown.on_post_page(html, page({"vi_status": "translated"}), {})
        self.assertNotIn("noindex", out)

    def test_fork_site_disables_comments(self):
        p = page({"comments": True})
        self.assertEqual(fork_site.on_page_markdown("x", p, {}, None), "x")
        self.assertIs(p.meta["comments"], False)


if __name__ == "__main__":
    unittest.main()
