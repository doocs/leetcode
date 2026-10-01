import hashlib
import json
import shutil
import sys
import tempfile
import unittest
from contextlib import redirect_stdout
from io import StringIO
from pathlib import Path

import yaml

sys.path.insert(0, str(Path(__file__).resolve().parent))
from helpers import engine_dir, use_hooks  # noqa: E402

use_hooks()
import build_vi  # noqa: E402

TWO_SUM = "solution/0000-0099/0001.Two Sum"
EN = {
    TWO_SUM: (
        "---\ncomments: true\ndifficulty: Easy\ntags:\n    - Array\n---\n\n"
        "# [1. Two Sum](https://leetcode.com/problems/two-sum)\n\n"
        "## Description\n"
    ),
    "solution/0000-0099/0002.Add Two Numbers": (
        "---\ncomments: true\ndifficulty: Medium\n---\n\n"
        "# [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers)\n"
    ),
    "solution/0100-0199/0104.Maximum Depth of Binary Tree": (
        "---\ndifficulty: Easy\n---\n\n"
        "# [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/x)\n"
    ),
    "lcci/01.01.Is Unique": (
        "---\ndifficulty: Easy\n---\n\n"
        "# [01.01. Is Unique](https://leetcode.cn/problems/is-unique-lcci)\n"
    ),
}
VI_TWO_SUM = (
    "---\ncomments: true\ndifficulty: Easy\ntags:\n    - Array\n---\n\n"
    "# [1. Two Sum](https://leetcode.com/problems/two-sum)\n\n"
    "## Mô tả\n"
)


def digest(path):
    return hashlib.sha256(path.read_bytes()).hexdigest()


class BuildViTest(unittest.TestCase):
    def setUp(self):
        self.tmp = Path(tempfile.mkdtemp())
        self.addCleanup(shutil.rmtree, self.tmp)
        self.work = self.tmp / "work"
        (self.work / "docs-en" / "stylesheets").mkdir(parents=True)
        (self.work / "docs-en" / "stylesheets" / "extra.css").write_text("/* */")
        (self.work / "docs" / "javascripts").mkdir(parents=True)
        (self.work / "docs" / "javascripts" / "mathjax.js").write_text("//")
        shutil.copy2(engine_dir() / "build_site.py", self.work)
        for rel, text in EN.items():
            path = self.work / rel / "README_EN.md"
            path.parent.mkdir(parents=True)
            path.write_text(text, encoding="utf-8")
        self.vi = self.tmp / "vi"
        self.reports = self.tmp / "units"
        self.reports.mkdir()
        self.write_vi(TWO_SUM, VI_TWO_SUM)
        self.write_report(TWO_SUM)
        self.static = self.tmp / "static"
        self.static.mkdir()
        (self.static / "index.md").write_text(
            "# Home\n\n<!-- vi:progress -->\n\n[repo](https://github.com/%REPO%)\n",
            encoding="utf-8",
        )

    def write_vi(self, rel, text):
        path = self.vi / rel / "README.md"
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text, encoding="utf-8")

    def write_report(self, rel, state="verified", **overrides):
        report = {
            "state": state,
            "source": {"sha256": digest(self.work / rel / "README_EN.md")},
            "target": {
                "path": f"vi/{rel}/README.md",
                "sha256": digest(self.vi / rel / "README.md"),
            },
        }
        for key, value in overrides.items():
            report[key].update(value)
        name = rel.rsplit("/", 1)[-1].split(".")[0]
        (self.reports / f"{name}.yaml").write_text(yaml.safe_dump(report))

    def build(self, only=None):
        out = StringIO()
        with redirect_stdout(out):
            nav = build_vi.build(
                self.work,
                self.vi,
                self.static,
                "owner/leetcode",
                only=only,
                reports_dir=self.reports,
            )
        self.log = out.getvalue()
        return nav

    def page(self, rel):
        return (self.work / "docs-vi" / rel).read_text(encoding="utf-8")

    def status(self):
        return json.loads(self.page(".vi_status.json"))

    def test_verified_translation_is_published(self):
        self.build()
        text = self.page("lc/1.md")
        self.assertIn("## Mô tả", text)
        meta = build_vi.frontmatter(text)
        self.assertEqual(meta["vi_status"], "translated")
        self.assertEqual(meta["difficulty"], "Easy")
        self.assertEqual(meta["tags"], ["Array"])
        self.assertNotIn("template", meta)
        self.assertEqual(
            meta["edit_url"],
            "https://github.com/owner/leetcode/edit/main/"
            "vi/solution/0000-0099/0001.Two%20Sum/README.md",
        )

    def test_source_change_marks_outdated(self):
        en = self.work / TWO_SUM / "README_EN.md"
        en.write_text(EN[TWO_SUM] + "\nNew sentence.\n", encoding="utf-8")
        self.build()
        self.assertEqual(
            build_vi.frontmatter(self.page("lc/1.md"))["vi_status"], "outdated"
        )
        self.assertEqual(self.status()["lc/1.md"], "outdated")
        self.assertIn("English source changed", self.log)

    def test_unverified_translation_falls_back_to_stub(self):
        cases = {
            "no unit report": lambda: (self.reports / "0001.yaml").unlink(),
            "report state is 'translated'": lambda: self.write_report(
                TWO_SUM, state="translated"
            ),
            "translation changed after review": lambda: self.write_vi(
                TWO_SUM, VI_TWO_SUM + "\nSửa sau khi review.\n"
            ),
        }
        for reason, mutate in cases.items():
            with self.subTest(reason):
                self.setUp()
                mutate()
                self.build()
                meta = build_vi.frontmatter(self.page("lc/1.md"))
                self.assertEqual(meta["vi_status"], "stub")
                self.assertIn(f"not published: {reason}", self.log)

    def test_stub_page(self):
        self.build()
        text = self.page("lc/2.md")
        meta = build_vi.frontmatter(text)
        self.assertEqual(meta["template"], "vi_stub.html")
        self.assertEqual(meta["vi_status"], "stub")
        self.assertEqual(meta["difficulty"], "Medium")
        self.assertEqual(meta["search"], {"exclude": True})
        self.assertIs(meta["glightbox"], False)
        self.assertNotIn("tags", meta)
        self.assertIn(
            "# [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers)",
            text,
        )
        self.assertIn("[English](../../../en/lc/2/)", text)
        self.assertIn("[中文](../../../lc/2/)", text)
        lcci = self.page("lcci/1.1.md")
        self.assertIn("[English](../../../en/lcci/1.1/)", lcci)

    def test_index_pages_list_every_problem(self):
        self.build()
        text = self.page("lc/0000-0099.md")
        self.assertIn("Số bài: 2 · đã dịch: 1", text)
        self.assertIn("- ✅ [1. Two Sum](1.md)", text)
        self.assertIn("- [2. Add Two Numbers](2.md)", text)
        self.assertIn(
            "- [104. Maximum Depth of Binary Tree](104.md)",
            self.page("lc/0100-0199.md"),
        )
        self.assertIn("- [1.1. Is Unique](1.1.md)", self.page("lcci/index.md"))

    def test_nav_lists_only_published_problems(self):
        nav = self.build()
        self.assertEqual(
            nav,
            "nav:\n"
            "  - Trang chủ: index.md\n"
            "  - LeetCode:\n"
            "    - 0000–0099:\n"
            "      - Mục lục: lc/0000-0099.md\n"
            "      - 1. Two Sum: lc/1.md\n"
            "    - 0100–0199:\n"
            "      - Mục lục: lc/0100-0199.md\n"
            "  - Cracking the Coding Interview:\n"
            "    - Mục lục: lcci/index.md\n"
            "  - Luyện tập theo chủ đề: tags.md\n"
            "  - Contest: contest.md\n",
        )

    def test_status_progress_and_static(self):
        self.build()
        status = self.status()
        self.assertEqual(status["lc/1.md"], "translated")
        self.assertEqual(status["lc/2.md"], "stub")
        self.assertEqual(status["contest.md"], "stub")
        index = self.page("index.md")
        self.assertIn("Hiện đã dịch **1** / 4 bài (25.0%).", index)
        self.assertIn("https://github.com/owner/leetcode", index)
        self.assertTrue((self.work / "docs-vi" / "stylesheets" / "extra.css").is_file())
        self.assertTrue(
            (self.work / "docs-vi" / "javascripts" / "mathjax.js").is_file()
        )

    def test_only_limits_pages(self):
        self.build(only={"solution/0000-0099/0002.Add Two Numbers"})
        self.assertTrue((self.work / "docs-vi" / "lc" / "2.md").is_file())
        self.assertFalse((self.work / "docs-vi" / "lc" / "1.md").exists())
        self.assertFalse((self.work / "docs-vi" / "lcci" / "1.1.md").exists())

    def test_heading_number_mismatch_fails(self):
        self.write_vi(TWO_SUM, "# [3. Wrong](https://leetcode.com/problems/two-sum)\n")
        self.write_report(TWO_SUM)
        with self.assertRaises(build_vi.BuildError):
            self.build()

    def test_with_meta_without_frontmatter(self):
        text = build_vi.with_meta("# T\n", {"vi_status": "translated"})
        self.assertEqual(text, '---\nvi_status: "translated"\n---\n\n# T\n')


if __name__ == "__main__":
    unittest.main()
