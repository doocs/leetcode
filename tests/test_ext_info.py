import sys
import unittest
from pathlib import Path
from types import SimpleNamespace

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import ext_info as ei  # noqa: E402


def _page(meta, edit_url="https://github.com/doocs/leetcode/edit/main/x/README.md"):
    return SimpleNamespace(meta=meta, edit_url=edit_url, url="lc/1/")


class BadgeTest(unittest.TestCase):
    def test_escapes_html(self):
        self.assertEqual(
            ei._badge("分数", '<img src="x">'),
            '<span class="lc-badge">'
            '<span class="lc-badge__label">分数</span>'
            '<span class="lc-badge__value">&lt;img src=&quot;x&quot;&gt;</span>'
            "</span>",
        )


class AddDifficultyInfoTest(unittest.TestCase):
    def test_skips_when_no_difficulty_or_rating(self):
        md = "# Title\n\nbody"
        self.assertEqual(ei.add_difficulty_info(md, _page({})), md)
        self.assertEqual(
            ei.add_difficulty_info(md, _page({"source": "第 1 场周赛 Q1"})),
            md,
        )

    def test_inserts_css_badges_after_heading(self):
        md = "# [2155. 分组得分最高的所有下标](https://leetcode.cn/problems/x)\n\n正文"
        out = ei.add_difficulty_info(
            md,
            _page(
                {
                    "difficulty": "中等",
                    "rating": 1390,
                    "source": "第 278 场周赛 Q2",
                }
            ),
        )
        self.assertIn('<p class="lc-badges">', out)
        self.assertIn("来源", out)
        self.assertIn("第 278 场周赛 Q2", out)
        self.assertIn("难度", out)
        self.assertIn("中等", out)
        self.assertIn("分数", out)
        self.assertIn("1390", out)
        self.assertNotIn("img.shields.io", out)
        self.assertNotIn("<img", out)
        heading, badges, rest = out.split("\n\n", 2)
        self.assertTrue(heading.startswith("# [2155."))
        self.assertTrue(badges.startswith('<p class="lc-badges">'))
        self.assertIn("正文", rest)

    def test_english_labels_for_readme_en(self):
        out = ei.add_difficulty_info(
            "# Two Sum\n\nbody",
            _page(
                {"difficulty": "Easy", "rating": 1234, "source": "Weekly Contest 1 Q1"},
                edit_url="https://github.com/doocs/leetcode/edit/main/x/README_EN.md",
            ),
        )
        self.assertIn("Source", out)
        self.assertIn("Difficulty", out)
        self.assertIn("Rating", out)
        self.assertNotIn("难度", out)

    def test_leaves_markdown_without_heading(self):
        md = "no heading here"
        self.assertEqual(
            ei.add_difficulty_info(md, _page({"difficulty": "简单"})),
            md,
        )


if __name__ == "__main__":
    unittest.main()
