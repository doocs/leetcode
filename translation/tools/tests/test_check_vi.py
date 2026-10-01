import sys
import unittest
from pathlib import Path

TOOLS = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(TOOLS))
import check_vi  # noqa: E402

SRC = """---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [1. Two Sum](https://leetcode.com/problems/two-sum)

## Description

<p>Return the indices of <code>nums</code> where $i < j$.</p>

<pre>
<strong>Input:</strong> nums = [2,7], target = 9
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> Because they add up.
</pre>

### Solution 1: Hash Table

We use `d` and return the answer, see [x](/solution/a/README_EN.md).

<!-- tabs:start -->

#### Python3

```python
print(1)
```

<!-- tabs:end -->
"""

DST = """---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [1. Two Sum](https://leetcode.com/problems/two-sum)

## Mô tả

<p>Trả về chỉ số của <code>nums</code> sao cho $i < j$.</p>

<pre>
<strong>Đầu vào:</strong> nums = [2,7], target = 9
<strong>Đầu ra:</strong> [0,1]
<strong>Giải thích:</strong> Vì tổng của chúng bằng target.
</pre>

### Lời giải 1: Bảng băm

Ta dùng `d` và trả về đáp án, xem [x](/solution/a/README_EN.md).

<!-- tabs:start -->

#### Python3

```python
print(1)
```

<!-- tabs:end -->
"""


class CheckViTest(unittest.TestCase):
    def errors(self, dst):
        return check_vi.check_pair(SRC, dst)[0]

    def test_good_translation_passes(self):
        errors, warnings = check_vi.check_pair(SRC, DST)
        self.assertEqual(errors, [])
        self.assertEqual(warnings, [])

    def test_code_change_fails(self):
        self.assertTrue(
            any(
                "code_fences" in e
                for e in self.errors(DST.replace("print(1)", "print(2)"))
            )
        )

    def test_example_data_change_fails(self):
        bad = DST.replace("nums = [2,7], target = 9", "nums = [2, 7], target = 9")
        self.assertTrue(any("example data" in e for e in self.errors(bad)))

    def test_frontmatter_change_fails(self):
        bad = DST.replace("difficulty: Easy", "difficulty: Dễ")
        self.assertIn("frontmatter: differs from the source", self.errors(bad))

    def test_title_change_fails(self):
        bad = DST.replace("# [1. Two Sum]", "# [1. Tổng hai số]")
        self.assertTrue(any(e.startswith("h1:") for e in self.errors(bad)))

    def test_math_and_inline_code_change_fails(self):
        self.assertTrue(
            any(
                e.startswith("math")
                for e in self.errors(DST.replace("$i < j$", "$i \\le j$"))
            )
        )
        self.assertTrue(
            any(
                e.startswith("inline_code")
                for e in self.errors(DST.replace("`d`", "`dict`"))
            )
        )

    def test_marker_and_url_change_fails(self):
        self.assertTrue(
            any(
                e.startswith("markers")
                for e in self.errors(DST.replace("<!-- problem:start -->\n", ""))
            )
        )
        self.assertTrue(
            any(
                e.startswith("urls")
                for e in self.errors(DST.replace("README_EN.md", "README.md"))
            )
        )

    def test_tab_heading_change_fails(self):
        self.assertIn(
            "tabs: code-tab headings differ",
            self.errors(DST.replace("#### Python3", "#### Python")),
        )

    def test_html_tag_changes_fail(self):
        for old, new in (
            ("<code>nums</code>", "nums"),
            ("<strong>Đầu ra:</strong>", "Đầu ra:"),
            ("</p>\n\n<pre>", "\n\n<pre>"),
            ("chỉ số của", "chỉ số<br>của"),
        ):
            with self.subTest(old):
                bad = DST.replace(old, new)
                self.assertNotEqual(bad, DST)
                self.assertTrue(
                    any(
                        e.startswith(("html_tags", "inline_code"))
                        for e in self.errors(bad)
                    )
                )

    def test_block_count_changes_fail(self):
        added = DST.replace("## Mô tả\n", "## Mô tả\n\nMột đoạn thêm.\n")
        self.assertTrue(any(e.startswith("blocks") for e in self.errors(added)))
        removed = DST.replace(
            "Ta dùng `d` và trả về đáp án, xem [x](/solution/a/README_EN.md).\n\n", ""
        )
        self.assertTrue(any(e.startswith("blocks") for e in self.errors(removed)))

    def test_untranslated_prose_warns(self):
        bad = DST.replace(
            "Ta dùng `d` và trả về đáp án", "We use `d` and return the answer"
        )
        errors, warnings = check_vi.check_pair(SRC, bad)
        self.assertEqual(errors, [])
        self.assertEqual(len(warnings), 1)

    def test_source_for(self):
        target = check_vi.REPO / "vi/solution/0000-0099/0001.Two Sum/README.md"
        self.assertEqual(
            check_vi.source_for(target),
            check_vi.REPO / "solution/0000-0099/0001.Two Sum/README_EN.md",
        )


if __name__ == "__main__":
    unittest.main()
