import json
import sys
import tempfile
import unittest
from pathlib import Path
from types import SimpleNamespace

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import edit_map  # noqa: E402
import revision_date  # noqa: E402


class CommitDatesFromLogTest(unittest.TestCase):
    def test_keeps_newest_commit_and_stops(self):
        log = [
            "2024-06-01T00:00:00+08:00\n",
            "solution/0000-0099/0001.Two Sum/README.md\n",
            "\n",
            "2020-01-01T00:00:00Z\n",
            "solution/0000-0099/0001.Two Sum/README.md\n",
            "lcof/剑指 Offer/README.md\n",
        ]
        found = revision_date.commit_dates_from_log(
            log,
            {
                "solution/0000-0099/0001.Two Sum/README.md",
                "lcof/剑指 Offer/README.md",
            },
        )
        self.assertEqual(
            found["solution/0000-0099/0001.Two Sum/README.md"], "2024-06-01"
        )
        self.assertEqual(found["lcof/剑指 Offer/README.md"], "2020-01-01")

    def test_ignores_unrelated_paths(self):
        found = revision_date.commit_dates_from_log(
            ["2024-01-02T00:00:00+00:00\n", "other/file.md\n"],
            {"solution/a.md"},
        )
        self.assertEqual(found, {})


class IndexDatesTest(unittest.TestCase):
    def test_range_index_uses_newest_problem(self):
        mapping = {
            "lc/1.md": "solution/a.md",
            "lc/100.md": "solution/b.md",
            "contest.md": "solution/CONTEST_README.md",
        }
        dates = {
            "solution/a.md": "2024-01-02",
            "solution/b.md": "2023-05-01",
            "solution/CONTEST_README.md": "2024-08-09",
        }
        indexed = revision_date.index_dates(mapping, dates)
        self.assertEqual(indexed["lc/1.md"], "2024-01-02")
        self.assertEqual(indexed["lc/0000-0099.md"], "2024-01-02")
        self.assertEqual(indexed["lc/0100-0199.md"], "2023-05-01")
        self.assertEqual(indexed["contest.md"], "2024-08-09")


class OnPageMarkdownTest(unittest.TestCase):
    def test_sets_problem_and_range_dates(self):
        revision_date.reset_cache()
        edit_map.reset_cache()
        original = revision_date.commit_dates
        revision_date.commit_dates = lambda paths: {
            "solution/0000-0099/0001.Two Sum/README.md": "2024-01-02",
            "solution/0000-0099/0002.Add Two Numbers/README.md": "2024-06-01",
        }
        try:
            with tempfile.TemporaryDirectory() as tmp:
                docs = Path(tmp)
                (docs / ".edit_map.json").write_text(
                    json.dumps(
                        {
                            "lc/1.md": "solution/0000-0099/0001.Two Sum/README.md",
                            "lc/2.md": "solution/0000-0099/0002.Add Two Numbers/README.md",
                        }
                    ),
                    encoding="utf-8",
                )
                config = {"docs_dir": str(docs)}

                def page(src, stamp="2026-10-17"):
                    return SimpleNamespace(
                        file=SimpleNamespace(src_path=src), update_date=stamp
                    )

                problem = page("lc/1.md")
                returned = revision_date.on_page_markdown("body", problem, config, None)
                self.assertEqual(returned, "body")
                self.assertEqual(problem.update_date, "2024-01-02")

                index = page("lc/0000-0099.md")
                revision_date.on_page_markdown("# range", index, config, None)
                self.assertEqual(index.update_date, "2024-06-01")

                home = page("index.md")
                revision_date.on_page_markdown("home", home, config, None)
                self.assertEqual(home.update_date, "2026-10-17")
        finally:
            revision_date.commit_dates = original
            revision_date.reset_cache()
            edit_map.reset_cache()
