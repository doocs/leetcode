import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import thinking_block as tb  # noqa: E402

ZH = """### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> 若枚举所有数对，时间复杂度为 $O(n^2)$。
>
> 对当前元素 $x$，先查询再插入。

<!-- thinking:end -->

我们可以使用一个哈希表。
"""

EN = """### Solution 1: Hash Table

<!-- thinking:start -->

> **Thinking**
>
> The first idea is a nested loop.

<!-- thinking:end -->

We can use a hash table.
"""


class ThinkingBlockTest(unittest.TestCase):
    def test_converts_chinese_blockquote(self):
        out = tb.convert_thinking_blocks(ZH)
        self.assertIn('!!! thinking "思考"', out)
        self.assertIn("    若枚举所有数对，时间复杂度为 $O(n^2)$。", out)
        self.assertIn("    对当前元素 $x$，先查询再插入。", out)
        self.assertNotIn("<!-- thinking:start -->", out)
        self.assertNotIn("> **思考**", out)
        self.assertIn("我们可以使用一个哈希表。", out)

    def test_converts_english_blockquote(self):
        out = tb.convert_thinking_blocks(EN)
        self.assertIn('!!! thinking "Thinking"', out)
        self.assertIn("    The first idea is a nested loop.", out)
        self.assertNotIn("> **Thinking**", out)
        self.assertIn("We can use a hash table.", out)

    def test_converts_multiple_blocks(self):
        out = tb.convert_thinking_blocks(ZH + "\n" + EN)
        self.assertEqual(out.count("!!! thinking"), 2)
        self.assertIn('!!! thinking "思考"', out)
        self.assertIn('!!! thinking "Thinking"', out)

    def test_leaves_pages_without_markers(self):
        src = "### 方法一\n\n直接写解法。\n"
        self.assertEqual(tb.convert_thinking_blocks(src), src)

    def test_on_page_markdown_delegates(self):
        self.assertEqual(
            tb.on_page_markdown(EN, None, None, None),
            tb.convert_thinking_blocks(EN),
        )


if __name__ == "__main__":
    unittest.main()
