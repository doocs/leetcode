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

### Lời giải 1: Hash Table

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
        bad = DST.replace("print(1)", "print(2)")
        self.assertTrue(
            any("code_fences" in error for error in self.errors(bad))
        )

    def test_explanatory_comment_translation_passes(self):
        src = SRC.replace("print(1)", "# Print the answer\nprint(1)")
        dst = DST.replace("print(1)", "# In ra dap an\nprint(1)")
        self.assertNotIn("code_fences", " ".join(check_vi.check_pair(src, dst)[0]))

    def test_comment_marker_inside_string_is_protected(self):
        src = SRC.replace("print(1)", 'print("# source")')
        dst = DST.replace("print(1)", 'print("# target")')
        self.assertIn("code_fences", " ".join(check_vi.check_pair(src, dst)[0]))

    def test_tool_directive_comment_is_protected(self):
        src = SRC.replace("print(1)", "# noqa\nprint(1)")
        dst = DST.replace("print(1)", "# bo qua\nprint(1)")
        self.assertIn("code_fences", " ".join(check_vi.check_pair(src, dst)[0]))

    def test_example_data_change_fails(self):
        bad = DST.replace(
            "nums = [2,7], target = 9",
            "nums = [2, 7], target = 9",
        )
        self.assertTrue(
            any("example data" in error for error in self.errors(bad))
        )

    def test_frontmatter_change_fails(self):
        bad = DST.replace("difficulty: Easy", "difficulty: Dễ")
        self.assertIn(
            "frontmatter: differs from the source",
            self.errors(bad),
        )

    def test_title_change_fails(self):
        bad = DST.replace("# [1. Two Sum]", "# [1. Tổng hai số]")
        self.assertTrue(
            any(error.startswith("h1:") for error in self.errors(bad))
        )

    def test_math_and_inline_code_change_fail(self):
        bad_math = DST.replace("$i < j$", "$i \\le j$")
        self.assertTrue(
            any(
                error.startswith("math")
                for error in self.errors(bad_math)
            )
        )

        bad_code = DST.replace("`d`", "`dict`")
        self.assertTrue(
            any(
                error.startswith("inline_code")
                for error in self.errors(bad_code)
            )
        )

    def test_marker_and_url_change_fail(self):
        bad_marker = DST.replace("<!-- problem:start -->\n", "")
        self.assertTrue(
            any(
                error.startswith("markers")
                for error in self.errors(bad_marker)
            )
        )

        bad_url = DST.replace("README_EN.md", "README.md")
        self.assertTrue(
            any(
                error.startswith("urls")
                for error in self.errors(bad_url)
            )
        )

    def test_tab_heading_change_fails(self):
        bad = DST.replace("#### Python3", "#### Python")
        self.assertIn(
            "tabs: code-tab headings differ",
            self.errors(bad),
        )

    def test_html_tag_change_fails(self):
        bad = DST.replace("<code>nums</code>", "nums")
        self.assertTrue(
            any(
                error.startswith(("html_tags", "inline_code"))
                for error in self.errors(bad)
            )
        )

    def test_paragraph_split_is_allowed(self):
        split = DST.replace(
            "Ta dùng `d` và trả về đáp án, xem "
            "[x](/solution/a/README_EN.md).",
            "Ta dùng `d` và trả về đáp án.\n\n"
            "Xem [x](/solution/a/README_EN.md).",
        )
        errors, _warnings = check_vi.check_pair(SRC, split)
        self.assertEqual(errors, [])

    def test_untranslated_prose_warns(self):
        bad = DST.replace(
            "Ta dùng `d` và trả về đáp án",
            "We use `d` and return the answer",
        )
        errors, warnings = check_vi.check_pair(SRC, bad)
        self.assertEqual(errors, [])
        self.assertEqual(len(warnings), 1)

    def test_source_for(self):
        target = (
            check_vi.REPO
            / "vi/solution/0000-0099/0001.Two Sum/README.md"
        )
        self.assertEqual(
            check_vi.source_for(target),
            check_vi.REPO
            / "solution/0000-0099/0001.Two Sum/README_EN.md",
        )


if __name__ == "__main__":
    unittest.main()
