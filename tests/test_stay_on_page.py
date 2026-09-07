import re
import sys
import tempfile
import unittest
from pathlib import Path
from types import SimpleNamespace

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import stay_on_page as sop  # noqa: E402

CN_CONFIG = {"site_url": "https://leetcode.doocs.org", "site_dir": "site"}
EN_CONFIG = {"site_url": "https://leetcode.doocs.org/en", "site_dir": "site/en"}

HTML = """<html><head>
<link rel="alternate" href="/en/" hreflang="en">
<link rel="alternate" href="/" hreflang="zh">
</head><body>
<a href="/en/" hreflang="en">English</a>
<a href="/" hreflang="zh">中文</a>
</body></html>"""

MINIFIED = (
    "<html><head>"
    "<link rel=alternate href=/en/ hreflang=en>"
    "<link rel=alternate href=/ hreflang=zh>"
    "</head><body>"
    "<a href=/en/ hreflang=en>English</a>"
    "<a href=/ hreflang=zh>中文</a>"
    "</body></html>"
)

SITEMAP = """<?xml version='1.0' encoding='UTF-8'?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<url><loc>https://leetcode.doocs.org/lc/100/</loc></url>
<url><loc>https://leetcode.doocs.org/lcof/3/</loc></url>
<url><loc>https://leetcode.doocs.org/</loc></url>
</urlset>
"""

SITEMAP_EN = """<?xml version='1.0' encoding='UTF-8'?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<url><loc>https://leetcode.doocs.org/en/lc/100/</loc></url>
<url><loc>https://leetcode.doocs.org/en/</loc></url>
</urlset>
"""


def _hrefs(html: str, tag: str) -> dict[str, str]:
    found = {}
    for m in re.finditer(
        rf"""<{tag}\b(?=[^>]*\bhreflang=(?P<lq>["']?)(?P<lang>zh|en|x-default)(?P=lq))"""
        rf"""[^>]*\bhref=(?P<hq>["']?)(?P<href>[^"'\s>]*)(?P=hq)""",
        html,
        re.IGNORECASE,
    ):
        found[m.group("lang").lower()] = m.group("href")
    return found


def _sitemap_hrefs(html: str) -> list[str]:
    return re.findall(
        r"""<link\b[^>]*\brel=["']?sitemap["']?[^>]*\bhref=["']?([^"'\s>]+)""",
        html,
        flags=re.IGNORECASE,
    )


def _page_sitemaps(html: str) -> list[str]:
    hrefs = re.findall(
        r"""<link\b[^>]*\bhref=["']?([^"'\s>]+)""",
        html,
        flags=re.IGNORECASE,
    )
    return [
        href
        for href in hrefs
        if "sitemap.xml" in href.lower()
        and not re.fullmatch(
            r"https://leetcode\.doocs\.org(?:/en)?/sitemap\.xml", href, flags=re.I
        )
        and href not in ("/sitemap.xml", "/en/sitemap.xml")
    ]


def _xhtml_hrefs(xml: str, loc: str) -> dict[str, str]:
    found = {}
    for m in re.finditer(
        rf"<url>\s*<loc>{re.escape(loc)}</loc>(.*?)</url>",
        xml,
        flags=re.DOTALL,
    ):
        for link in re.finditer(
            r'hreflang="([^"]+)"[^>]*href="([^"]+)"|href="([^"]+)"[^>]*hreflang="([^"]+)"',
            m.group(1),
        ):
            if link.group(1):
                found[link.group(1)] = link.group(2)
            else:
                found[link.group(4)] = link.group(3)
    return found


class RelativeHrefTest(unittest.TestCase):
    def test_same_page(self):
        self.assertEqual(sop._relative_href("/lc/100/", "/lc/100/"), "./")

    def test_cn_to_en_problem(self):
        self.assertEqual(
            sop._relative_href("/lc/100/", "/en/lc/100/"), "../../en/lc/100/"
        )

    def test_en_to_cn_problem(self):
        self.assertEqual(
            sop._relative_href("/en/lc/100/", "/lc/100/"), "../../../lc/100/"
        )

    def test_lcof_to_en_home(self):
        self.assertEqual(sop._relative_href("/lcof/3/", "/en/"), "../../en/")

    def test_cn_home_to_en_home(self):
        self.assertEqual(sop._relative_href("/", "/en/"), "en/")


class StayOnPageTest(unittest.TestCase):
    def _run(self, url: str, config: dict, html: str = HTML) -> str:
        return sop.on_post_page(html, SimpleNamespace(url=url), config)

    def test_bilingual_cn_page(self):
        out = self._run("lc/100/", CN_CONFIG)
        self.assertEqual(_hrefs(out, "a"), {"zh": "./", "en": "../../en/lc/100/"})
        self.assertEqual(
            _hrefs(out, "link"),
            {
                "zh": "https://leetcode.doocs.org/lc/100/",
                "en": "https://leetcode.doocs.org/en/lc/100/",
                "x-default": "https://leetcode.doocs.org/lc/100/",
            },
        )
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])
        self.assertIn("XMLHttpRequest.prototype.open", out)
        self.assertEqual(_page_sitemaps(out), [])

    def test_bilingual_en_page(self):
        out = self._run("lc/100/", EN_CONFIG)
        self.assertEqual(_hrefs(out, "a"), {"zh": "../../../lc/100/", "en": "./"})
        self.assertEqual(
            _hrefs(out, "link"),
            {
                "zh": "https://leetcode.doocs.org/lc/100/",
                "en": "https://leetcode.doocs.org/en/lc/100/",
                "x-default": "https://leetcode.doocs.org/lc/100/",
            },
        )
        self.assertEqual(
            _sitemap_hrefs(out), ["https://leetcode.doocs.org/en/sitemap.xml"]
        )
        self.assertEqual(_page_sitemaps(out), [])

    def test_lcof_has_no_en_alternate_link(self):
        out = self._run("lcof/3/", CN_CONFIG)
        self.assertEqual(_hrefs(out, "a"), {"zh": "./", "en": "../../en/"})
        links = _hrefs(out, "link")
        self.assertNotIn("en", links)
        self.assertEqual(
            links,
            {
                "zh": "https://leetcode.doocs.org/lcof/3/",
                "x-default": "https://leetcode.doocs.org/lcof/3/",
            },
        )
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])
        self.assertEqual(_page_sitemaps(out), [])
        self.assertNotIn("/lcof/3/sitemap.xml", out.split("<script>")[0])
        self.assertNotIn("./sitemap.xml", out)

    def test_lcof2_has_no_en_alternate_link(self):
        out = self._run("lcof2/76/", CN_CONFIG)
        self.assertEqual(_hrefs(out, "a")["en"], "../../en/")
        self.assertNotIn("en", _hrefs(out, "link"))
        self.assertEqual(
            _hrefs(out, "link")["x-default"],
            "https://leetcode.doocs.org/lcof2/76/",
        )

    def test_minified_html(self):
        out = self._run("lc/100/", CN_CONFIG, MINIFIED)
        self.assertEqual(_hrefs(out, "a")["en"], "../../en/lc/100/")
        self.assertEqual(_hrefs(out, "link")["en"], "https://leetcode.doocs.org/en/lc/100/")
        self.assertEqual(_hrefs(out, "link")["x-default"], "https://leetcode.doocs.org/lc/100/")
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])
        self.assertIn("XMLHttpRequest.prototype.open", out)

    def test_does_not_double_inject_sitemap(self):
        html = HTML.replace(
            "</head>",
            '<link rel="sitemap" href="https://leetcode.doocs.org/sitemap.xml"></head>',
        )
        out = self._run("lc/100/", CN_CONFIG, html)
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])

    def test_empty_output(self):
        self.assertEqual(sop.on_post_page("", SimpleNamespace(url="lc/100/"), CN_CONFIG), "")

    def test_alternate_without_origin_uses_root_absolute_pages(self):
        out = self._run("lcof/3/", {"site_url": "", "site_dir": "site"})
        self.assertEqual(
            _hrefs(out, "link"),
            {"zh": "/lcof/3/", "x-default": "/lcof/3/"},
        )
        self.assertNotIn("en", _hrefs(out, "link"))
        self.assertEqual(_sitemap_hrefs(out), ["/sitemap.xml"])
        self.assertEqual(_page_sitemaps(out), [])

    def test_range_index_is_bilingual_page_pair(self):
        out = self._run("lc/0100-0199/", CN_CONFIG)
        self.assertEqual(
            _hrefs(out, "link")["zh"], "https://leetcode.doocs.org/lc/0100-0199/"
        )
        self.assertEqual(
            _hrefs(out, "link")["en"], "https://leetcode.doocs.org/en/lc/0100-0199/"
        )
        self.assertNotIn("/lc/0100-0199/sitemap.xml", out.split("<script>")[0])
        self.assertEqual(_page_sitemaps(out), [])


class SitemapAnnotateTest(unittest.TestCase):
    def test_bilingual_and_lcof_cn_sitemap(self):
        xml = sop.annotate_sitemap(SITEMAP, "https://leetcode.doocs.org")
        self.assertIn('xmlns:xhtml="http://www.w3.org/1999/xhtml"', xml)
        self.assertEqual(
            _xhtml_hrefs(xml, "https://leetcode.doocs.org/lc/100/"),
            {
                "zh": "https://leetcode.doocs.org/lc/100/",
                "en": "https://leetcode.doocs.org/en/lc/100/",
                "x-default": "https://leetcode.doocs.org/lc/100/",
            },
        )
        lcof = _xhtml_hrefs(xml, "https://leetcode.doocs.org/lcof/3/")
        self.assertNotIn("en", lcof)
        self.assertEqual(
            lcof,
            {
                "zh": "https://leetcode.doocs.org/lcof/3/",
                "x-default": "https://leetcode.doocs.org/lcof/3/",
            },
        )
        self.assertEqual(
            _xhtml_hrefs(xml, "https://leetcode.doocs.org/")["en"],
            "https://leetcode.doocs.org/en/",
        )

    def test_en_sitemap_pairs_back_to_cn(self):
        xml = sop.annotate_sitemap(SITEMAP_EN, "https://leetcode.doocs.org")
        self.assertEqual(
            _xhtml_hrefs(xml, "https://leetcode.doocs.org/en/lc/100/"),
            {
                "zh": "https://leetcode.doocs.org/lc/100/",
                "en": "https://leetcode.doocs.org/en/lc/100/",
                "x-default": "https://leetcode.doocs.org/lc/100/",
            },
        )

    def test_on_post_build_writes_sitemap(self):
        with tempfile.TemporaryDirectory() as tmp:
            path = Path(tmp) / "sitemap.xml"
            path.write_text(SITEMAP, encoding="utf-8")
            sop.on_post_build(
                {"site_url": "https://leetcode.doocs.org", "site_dir": tmp}
            )
            xml = path.read_text(encoding="utf-8")
            self.assertIn('hreflang="en"', xml)
            self.assertIn("https://leetcode.doocs.org/en/lc/100/", xml)
            self.assertNotIn("https://leetcode.doocs.org/en/lcof/", xml)


if __name__ == "__main__":
    unittest.main()
