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

ORIGIN = "https://owner.github.io/leetcode"

HTML = """<html><head>
<link rel="alternate" href="/en/" hreflang="en">
<link rel="alternate" href="/" hreflang="zh">
<link rel="alternate" href="/vi/" hreflang="vi">
</head><body>
<a href="/en/" hreflang="en" class="md-select__link">English</a>
<a href="/" hreflang="zh" class="md-select__link">中文</a>
<a href="/vi/" hreflang="vi" class="md-select__link">Tiếng Việt</a>
</body></html>"""

MINIFIED = (
    "<html><head><link rel=alternate href=/vi/ hreflang=vi></head><body>"
    "<a href=/vi/ hreflang=vi class=md-select__link>Tiếng Việt</a></body></html>"
)

SITEMAP = f"""<?xml version='1.0' encoding='UTF-8'?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<url><loc>{ORIGIN}/vi/</loc></url>
<url><loc>{ORIGIN}/vi/lc/1/</loc></url>
<url><loc>{ORIGIN}/vi/lc/2/</loc></url>
<url><loc>{ORIGIN}/vi/contest/</loc></url>
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
            "docs": ["index.md", "lc/1.md", "lc/2.md", "lcof/3.md", "contest.md"],
            "docs-en": ["index.md", "lc/1.md", "lc/2.md", "contest.md"],
            "docs-vi": ["index.md", "lc/1.md", "lc/2.md", "contest.md"],
        }
        for docs, files in pages.items():
            for rel in files:
                path = self.tmp / docs / rel
                path.parent.mkdir(parents=True, exist_ok=True)
                path.write_text("# x\n")
        status = {"lc/1.md": "translated", "lc/2.md": "stub", "contest.md": "stub"}
        (self.tmp / "docs-vi" / ".vi_status.json").write_text(json.dumps(status))

    def config(self, lang):
        prefix = {"zh": "", "en": "/en", "vi": "/vi"}[lang]
        docs = {"zh": "docs", "en": "docs-en", "vi": "docs-vi"}[lang]
        return {
            "site_url": ORIGIN + prefix,
            "site_dir": str(self.tmp / ("site" + prefix)),
            "docs_dir": str(self.tmp / docs),
            "extra": {"site_lang": lang},
        }

    def render(self, lang, url, html=HTML):
        page = SimpleNamespace(url=url)
        return vi_switch.on_post_page(html, page, self.config(lang))

    def test_zh_translated_page(self):
        out = self.render("zh", "lc/1/")
        self.assertEqual(hrefs(out, "a")["vi"], "../../vi/lc/1/")
        self.assertEqual(hrefs(out, "link")["vi"], f"{ORIGIN}/vi/lc/1/")
        # zh/en entries stay for the upstream stay_on_page hook
        self.assertEqual(hrefs(out, "a")["en"], "/en/")
        self.assertNotIn("XMLHttpRequest", out)

    def test_zh_page_with_stub(self):
        out = self.render("zh", "lc/2/")
        self.assertEqual(hrefs(out, "a")["vi"], "../../vi/lc/2/")
        self.assertNotIn("vi", hrefs(out, "link"))

    def test_zh_only_page_falls_back_to_vi_home(self):
        out = self.render("zh", "lcof/3/")
        self.assertEqual(hrefs(out, "a")["vi"], "../../vi/")
        self.assertNotIn("vi", hrefs(out, "link"))

    def test_en_page(self):
        out = self.render("en", "lc/1/")
        self.assertEqual(hrefs(out, "a")["vi"], "../../../vi/lc/1/")

    def test_vi_translated_page(self):
        out = self.render("vi", "lc/1/")
        self.assertEqual(
            hrefs(out, "a"),
            {"en": "../../../en/lc/1/", "zh": "../../../lc/1/", "vi": "./"},
        )
        links = hrefs(out, "link")
        self.assertEqual(links["zh"], f"{ORIGIN}/lc/1/")
        self.assertEqual(links["en"], f"{ORIGIN}/en/lc/1/")
        self.assertEqual(links["vi"], f"{ORIGIN}/vi/lc/1/")
        self.assertEqual(links["x-default"], f"{ORIGIN}/lc/1/")
        self.assertIn("XMLHttpRequest.prototype.open", out)

    def test_vi_stub_drops_alternates(self):
        out = self.render("vi", "lc/2/")
        self.assertEqual(hrefs(out, "a")["zh"], "../../../lc/2/")
        self.assertEqual(hrefs(out, "link"), {})

    def test_vi_home(self):
        out = self.render("vi", "")
        self.assertEqual(hrefs(out, "a"), {"en": "../en/", "zh": "../", "vi": "./"})

    def test_minified_html(self):
        out = self.render("zh", "lc/1/", MINIFIED)
        self.assertEqual(hrefs(out, "a")["vi"], "../../vi/lc/1/")
        self.assertEqual(hrefs(out, "link")["vi"], f"{ORIGIN}/vi/lc/1/")

    def test_sitemap_drops_stubs(self):
        out = vi_switch.drop_stubs_from_sitemap(SITEMAP, self.config("vi"))
        self.assertIn(f"{ORIGIN}/vi/lc/1/", out)
        self.assertIn(f"<loc>{ORIGIN}/vi/</loc>", out)
        self.assertNotIn(f"{ORIGIN}/vi/lc/2/", out)
        self.assertNotIn(f"{ORIGIN}/vi/contest/", out)

    def test_post_build_rewrites_sitemap_files(self):
        site = Path(self.config("vi")["site_dir"])
        site.mkdir(parents=True)
        (site / "sitemap.xml").write_text(SITEMAP)
        (site / "sitemap.xml.gz").write_bytes(b"")
        vi_switch.on_post_build(self.config("vi"))
        self.assertNotIn("/vi/lc/2/", (site / "sitemap.xml").read_text())
        import gzip

        self.assertNotIn(
            b"/vi/lc/2/", gzip.decompress((site / "sitemap.xml.gz").read_bytes())
        )

    def test_errors_are_raised_in_strict_mode(self):
        def boom(*args):
            raise ValueError("engine changed")

        original = vi_switch.rewrite
        self.addCleanup(setattr, vi_switch, "rewrite", original)
        vi_switch.rewrite = boom
        page = SimpleNamespace(url="lc/1/")
        with mock.patch.dict(os.environ, {"VI_STRICT": ""}):
            self.assertEqual(
                vi_switch.on_post_page(HTML, page, self.config("zh")), HTML
            )
        with mock.patch.dict(os.environ, {"VI_STRICT": "1"}):
            with self.assertRaises(ValueError):
                vi_switch.on_post_page(HTML, page, self.config("zh"))

    def test_site_lang_fallback(self):
        self.assertEqual(vi_switch.site_lang({"site_dir": "site/vi"}), "vi")
        self.assertEqual(vi_switch.site_lang({"site_dir": "site/en"}), "en")
        self.assertEqual(vi_switch.site_lang({"site_dir": "site"}), "zh")


if __name__ == "__main__":
    unittest.main()
