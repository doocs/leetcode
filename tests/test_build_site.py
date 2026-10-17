import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT))
import build_site  # noqa: E402


class RangedNavTitleTest(unittest.TestCase):
    def test_index_page_title_is_the_range_label(self):
        items = [
            build_site.NavItem((1,), "1", "Two Sum", "lc/1.md"),
            build_site.NavItem((100,), "100", "Same Tree", "lc/100.md"),
        ]
        nav = build_site.format_ranged_nav(items)
        self.assertIn("      - 0000–0099: lc/0000-0099.md", nav)
        self.assertIn("      - 0100–0199: lc/0100-0199.md", nav)
        self.assertNotIn("目录", nav)
        self.assertNotIn("Index:", nav)
