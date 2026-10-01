import shutil
import sys
import tempfile
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from helpers import use_hooks  # noqa: E402

use_hooks()
import prepare  # noqa: E402

ZH = {
    "hooks": [
        "hooks/edit_url.py",
        "hooks/committer.py",
        "hooks/thinking_block.py",
        "hooks/ext_info.py",
        "hooks/tags.py",
        "hooks/stay_on_page.py",
    ],
    "plugins": ["tags", {"search": {}}, {"minify": {"minify_html": True}}],
    "extra": {"analytics": {"provider": "google"}},
}
UPSTREAM = "https://leetcode.doocs.org"


class PrepareTest(unittest.TestCase):
    def config(self, minify=True):
        return prepare.site_config(
            ZH, "https://leetcode-vi.example.com/", "owner/leetcode", UPSTREAM, minify
        )

    def test_site_settings(self):
        c = self.config()
        self.assertEqual(c["INHERIT"], "mkdocs.yml")
        self.assertEqual(c["site_url"], "https://leetcode-vi.example.com/vi")
        self.assertEqual(c["site_dir"], "site/vi")
        self.assertEqual(c["docs_dir"], "docs-vi")
        self.assertEqual(c["repo_url"], "https://github.com/owner/leetcode")
        self.assertEqual(c["theme"], {"language": "vi"})
        self.assertEqual(c["not_in_nav"], "/lc/*.md\n/lcci/*.md\n")
        self.assertIn("owner/leetcode", c["copyright"])

    def test_hooks(self):
        self.assertEqual(
            self.config()["hooks"],
            [
                "hooks/edit_url.py",
                "hooks/tags.py",
                "hooks/vi_markdown.py",
                "hooks/fork_site.py",
                "hooks/vi_switch.py",
            ],
        )

    def test_language_selector_points_to_upstream(self):
        extra = self.config()["extra"]
        self.assertIsNone(extra["analytics"])
        self.assertEqual(extra["upstream_site"], UPSTREAM)
        self.assertEqual(
            [(a["lang"], a["link"]) for a in extra["alternate"]],
            [("en", f"{UPSTREAM}/en/"), ("zh", f"{UPSTREAM}/"), ("vi", "/vi/")],
        )

    def test_minify_toggle(self):
        self.assertNotIn("plugins", self.config())
        self.assertEqual(self.config(minify=False)["plugins"], ["tags", {"search": {}}])

    def test_load_config_resolves_inherit_and_custom_tags(self):
        tmp = Path(tempfile.mkdtemp())
        self.addCleanup(shutil.rmtree, tmp)
        (tmp / "base.yml").write_text(
            "hooks:\n  - hooks/a.py\n"
            "markdown_extensions:\n"
            "  - pymdownx.emoji:\n"
            "      emoji_index: !!python/name:material.extensions.emoji.twemoji\n"
            "extra:\n  a: 1\n  b: {c: 2}\n"
        )
        (tmp / "child.yml").write_text("INHERIT: base.yml\nextra:\n  b: {d: 3}\n")
        cfg = prepare.load_config(tmp / "child.yml")
        self.assertEqual(cfg["hooks"], ["hooks/a.py"])
        self.assertEqual(cfg["extra"], {"a": 1, "b": {"c": 2, "d": 3}})

    def test_engine_ref_is_a_commit_sha(self):
        self.assertRegex(prepare.engine_ref(), r"^[0-9a-f]{40}$")

    def test_problem_dir_for(self):
        self.assertEqual(
            prepare.problem_dir_for("1"), "solution/0000-0099/0001.Two Sum"
        )
        self.assertEqual(
            prepare.problem_dir_for("lc/74"),
            "solution/0000-0099/0074.Search a 2D Matrix",
        )
        self.assertEqual(prepare.problem_dir_for("lcci/01.01"), "lcci/01.01.Is Unique")


if __name__ == "__main__":
    unittest.main()
