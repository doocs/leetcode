import gzip
import json
import os
import re
import shutil
import sys
import tempfile
import unittest
from pathlib import Path
from types import SimpleNamespace
from unittest import mock

sys.path.insert(0, str(Path(__file__).resolve().parent))
from helpers import use_hooks  # noqa: E402

use_hooks()
import vi_switch  # noqa: E402

SITE = "https://leetcode-vi.example.com"
UPSTREAM = "https://leetcode.doocs.org"

HTML = f"""<html><head>
<link rel="alternate" href="{UPSTREAM}/en/" hreflang="en">
<link rel="alternate" href="{UPSTREAM}/" hreflang="zh">
<link rel="alternate" href="/vi/" hreflang="vi">
</head><body>
<a href="{UPSTREAM}/en/" hreflang="en" class="md-select__link">English</a>
<a href="{UPSTREAM}/" hreflang="zh" class="md-select__link">中文</a>
<a href="/vi/" hreflang="vi" class="md-select__link">Tiếng Việt</a>
</body></html>"""

MINIFIED = (
    f"<html><head><link rel=alternate href=/vi/ hreflang=vi></head><body>"
    f"<a href={UPSTREAM}/ hreflang=zh class=md-select__link>中文</a>"
    "<a href=/vi/ hreflang=vi class=md-select__link>Tiếng Việt</a></body></html>"
)

SITEMAP = f"""<?xml version='1.0' encoding='UTF-8'?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<url><loc>{SITE}/vi/</loc></url>
<url><loc>{SITE}/vi/lc/1/</loc></url>
<url><loc>{SITE}/vi/lc/2/</loc></url>
<url><loc>{SITE}/vi/contest/</loc></url>
</urlset>
"""


def hrefs(html, tag):
    found = {}
    for m in re.finditer(
        rf"""<{tag}\b(?=[^>]*\bhreflang=["']?(?P<lang>zh|en|vi|x-default)\b)"""
        r"""[^>]*\bhref=["']?(?P<href>[^"'\s>]*)""",
        html,
    ):
        found[m.group("lang")] = m.group("href")
    return found


class ViSwitchTest(unittest.TestCase):
    def setUp(self):
        self.tmp = Path(tempfile.mkdtemp())
        self.addCleanup(shutil.rmtree, self.tmp)
        vi_switch._status_cache.clear()
        pages = {
            "docs": ["index.md", "lc/1.md", "lc/2.md", "contest.md"],
            "docs-en": ["index.md", "lc/1.md", "lc/2.md", "contest.md"],
            "docs-vi": [
                "index.md",
                "lc/1.md",
                "lc/2.md",
                "lcci/index.md",
                "contest.md",
            ],
        }
        for docs, files in pages.items():
            for rel in files:
                path = self.tmp / docs / rel
                path.parent.mkdir(parents=True, exist_ok=True)
                path.write_text("# x\n")
        status = {"lc/1.md": "translated", "lc/2.md": "stub", "contest.md": "stub"}
        (self.tmp / "docs-vi" / ".vi_status.json").write_text(json.dumps(status))
        self.config = {
            "site_url": f"{SITE}/vi",
            "site_dir": str(self.tmp / "site" / "vi"),
            "docs_dir": str(self.tmp / "docs-vi"),
            "extra": {"upstream_site": UPSTREAM},
        }

    def render(self, url, html=HTML):
        return vi_switch.on_post_page(html, SimpleNamespace(url=url), self.config)

    def test_translated_page(self):
        out = self.render("lc/1/")
        self.assertEqual(
            hrefs(out, "a"),
            {"en": f"{UPSTREAM}/en/lc/1/", "zh": f"{UPSTREAM}/lc/1/", "vi": "./"},
        )
        # zh/en alternates would not be reciprocal: only the page itself stays
        self.assertEqual(hrefs(out, "link"), {"vi": f"{SITE}/vi/lc/1/"})
        self.assertIn("XMLHttpRequest.prototype.open", out)

    def test_stub_page(self):
        out = self.render("lc/2/")
        self.assertEqual(hrefs(out, "a")["en"], f"{UPSTREAM}/en/lc/2/")
        self.assertEqual(hrefs(out, "link"), {})

    def test_home(self):
        out = self.render("")
        self.assertEqual(
            hrefs(out, "a"), {"en": f"{UPSTREAM}/en/", "zh": f"{UPSTREAM}/", "vi": "./"}
        )

    def test_vi_only_page_falls_back_to_upstream_home(self):
        out = self.render("lcci/")
        self.assertEqual(hrefs(out, "a")["zh"], f"{UPSTREAM}/")
        self.assertEqual(hrefs(out, "a")["en"], f"{UPSTREAM}/en/")

    def test_minified_html(self):
        out = self.render("lc/1/", MINIFIED)
        self.assertEqual(hrefs(out, "a"), {"zh": f"{UPSTREAM}/lc/1/", "vi": "./"})
        self.assertEqual(hrefs(out, "link"), {"vi": f"{SITE}/vi/lc/1/"})

    def test_default_upstream(self):
        del self.config["extra"]["upstream_site"]
        self.assertEqual(
            hrefs(self.render("lc/1/"), "a")["zh"], "https://leetcode.doocs.org/lc/1/"
        )

    def test_sitemap_drops_stubs(self):
        out = vi_switch.drop_stubs_from_sitemap(SITEMAP, self.config)
        self.assertIn(f"{SITE}/vi/lc/1/", out)
        self.assertIn(f"<loc>{SITE}/vi/</loc>", out)
        self.assertNotIn(f"{SITE}/vi/lc/2/", out)
        self.assertNotIn(f"{SITE}/vi/contest/", out)

    def test_post_build_rewrites_sitemap_files(self):
        site = Path(self.config["site_dir"])
        site.mkdir(parents=True)
        (site / "sitemap.xml").write_text(SITEMAP)
        (site / "sitemap.xml.gz").write_bytes(b"")
        vi_switch.on_post_build(self.config)
        self.assertNotIn("/vi/lc/2/", (site / "sitemap.xml").read_text())
        self.assertNotIn(
            b"/vi/lc/2/", gzip.decompress((site / "sitemap.xml.gz").read_bytes())
        )

    def test_errors_are_raised_in_strict_mode(self):
        def boom(*args):
            raise ValueError("engine changed")

        original = vi_switch.rewrite
        self.addCleanup(setattr, vi_switch, "rewrite", original)
        vi_switch.rewrite = boom
        with mock.patch.dict(os.environ, {"VI_STRICT": ""}):
            self.assertEqual(self.render("lc/1/"), HTML)
        with mock.patch.dict(os.environ, {"VI_STRICT": "1"}):
            with self.assertRaises(ValueError):
                self.render("lc/1/")


if __name__ == "__main__":
    unittest.main()
