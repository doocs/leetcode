import json
import os
import sys
import tempfile
import unittest
from pathlib import Path
from types import SimpleNamespace

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "hooks"))
import build_site  # noqa: E402
import committer  # noqa: E402
import edit_map  # noqa: E402
import edit_url  # noqa: E402


class StripEditUrlTest(unittest.TestCase):
    def test_removes_edit_url_from_frontmatter(self):
        src = (
            "---\n"
            "comments: true\n"
            "edit_url: https://github.com/doocs/leetcode/edit/main/x.md\n"
            "tags:\n"
            "    - 数组\n"
            "---\n\n"
            "# [1. Two Sum](https://leetcode.cn/problems/two-sum)\n"
        )
        out = build_site.strip_frontmatter_edit_url(src)
        self.assertNotIn("edit_url:", out)
        self.assertIn("comments: true", out)
        self.assertIn("tags:", out)
        self.assertIn("# [1. Two Sum]", out)

    def test_leaves_body_and_missing_field_alone(self):
        src = "---\ncomments: true\n---\n\n# title\n"
        self.assertEqual(build_site.strip_frontmatter_edit_url(src), src)
        self.assertEqual(build_site.strip_frontmatter_edit_url("# no frontmatter\n"), "# no frontmatter\n")


class CollectItemsEditMapTest(unittest.TestCase):
    def test_writes_map_and_strips_copied_frontmatter(self):
        with tempfile.TemporaryDirectory() as tmp:
            root = Path(tmp)
            problem = root / "solution" / "0000-0099" / "0001.Two Sum"
            problem.mkdir(parents=True)
            (problem / "README.md").write_text(
                "---\ncomments: true\nedit_url: https://example.com/cn\n---\n\n"
                "# [1. Two Sum](https://leetcode.cn/problems/two-sum)\n",
                encoding="utf-8",
            )
            (problem / "README_EN.md").write_text(
                "---\ncomments: true\nedit_url: https://example.com/en\n---\n\n"
                "# [1. Two Sum](https://leetcode.com/problems/two-sum)\n",
                encoding="utf-8",
            )
            cwd = os.getcwd()
            try:
                os.chdir(root)
                _nav_cn, _nav_en, edit_maps = build_site.collect_items()
                (root / "docs" / "contest.md").write_text("# contest\n", encoding="utf-8")
                (root / "docs-en" / "contest.md").write_text("# contest\n", encoding="utf-8")
                build_site.add_static_edit_mappings(edit_maps)
                build_site.write_edit_maps(edit_maps)
            finally:
                os.chdir(cwd)

            zh = (root / "docs" / "lc" / "1.md").read_text(encoding="utf-8")
            en = (root / "docs-en" / "lc" / "1.md").read_text(encoding="utf-8")
            self.assertNotIn("edit_url:", zh)
            self.assertNotIn("edit_url:", en)
            self.assertEqual(
                edit_maps["docs"]["lc/1.md"],
                "solution/0000-0099/0001.Two Sum/README.md",
            )
            self.assertEqual(
                edit_maps["docs-en"]["lc/1.md"],
                "solution/0000-0099/0001.Two Sum/README_EN.md",
            )
            self.assertEqual(
                edit_maps["docs"]["contest.md"], "solution/CONTEST_README.md"
            )
            dumped = json.loads((root / "docs" / ".edit_map.json").read_text(encoding="utf-8"))
            self.assertEqual(dumped["lc/1.md"], "solution/0000-0099/0001.Two Sum/README.md")


class EditUrlHookTest(unittest.TestCase):
    def tearDown(self):
        edit_map.reset_cache()

    def test_sets_edit_url_from_map(self):
        with tempfile.TemporaryDirectory() as tmp:
            docs = Path(tmp) / "docs"
            docs.mkdir()
            (docs / ".edit_map.json").write_text(
                json.dumps({"lc/1.md": "solution/0000-0099/0001.Two Sum/README.md"}),
                encoding="utf-8",
            )
            edit_map.reset_cache()
            page = SimpleNamespace(
                file=SimpleNamespace(src_path="lc/1.md"),
                meta={},
                edit_url=None,
            )
            out = edit_url.on_page_markdown("# hi", page, {"docs_dir": str(docs)}, None)
            self.assertEqual(out, "# hi")
            self.assertEqual(
                page.edit_url,
                "https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0001.Two%20Sum/README.md",
            )

    def test_falls_back_to_frontmatter(self):
        with tempfile.TemporaryDirectory() as tmp:
            docs = Path(tmp) / "docs"
            docs.mkdir()
            edit_map.reset_cache()
            page = SimpleNamespace(
                file=SimpleNamespace(src_path="index.md"),
                meta={"edit_url": "https://github.com/doocs/leetcode/edit/main/README.md"},
                edit_url=None,
            )
            edit_url.on_page_markdown("# hi", page, {"docs_dir": str(docs)}, None)
            self.assertEqual(
                page.edit_url,
                "https://github.com/doocs/leetcode/edit/main/README.md",
            )

    def test_clears_edit_url_when_unmapped(self):
        with tempfile.TemporaryDirectory() as tmp:
            docs = Path(tmp) / "docs"
            docs.mkdir()
            edit_map.reset_cache()
            page = SimpleNamespace(
                file=SimpleNamespace(src_path="tags.md"),
                meta={},
                edit_url="https://github.com/doocs/leetcode/edit/main/docs/tags.md",
            )
            edit_url.on_page_markdown("# hi", page, {"docs_dir": str(docs)}, None)
            self.assertIsNone(page.edit_url)


class CommitterLookupTest(unittest.TestCase):
    def tearDown(self):
        edit_map.reset_cache()

    def test_prefers_map_over_frontmatter(self):
        with tempfile.TemporaryDirectory() as tmp:
            docs = Path(tmp) / "docs"
            docs.mkdir()
            (docs / ".edit_map.json").write_text(
                json.dumps({"lc/1.md": "solution/0000-0099/0001.Two Sum/README.md"}),
                encoding="utf-8",
            )
            md = docs / "lc"
            md.mkdir()
            (md / "1.md").write_text(
                "---\nedit_url: https://github.com/doocs/leetcode/edit/main/stale.md\n---\n",
                encoding="utf-8",
            )
            edit_map.reset_cache()
            self.assertEqual(
                committer._repo_path_for_file("lc/1.md", str(docs), str(md / "1.md")),
                "solution/0000-0099/0001.Two Sum/README.md",
            )


if __name__ == "__main__":
    unittest.main()
