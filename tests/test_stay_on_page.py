import re
import sys
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


def _hrefs(html: str, tag: str) -> dict[str, str]:
    found = {}
    for m in re.finditer(
        rf"""<{tag}\b(?=[^>]*\bhreflang=(?P<lq>["']?)(?P<lang>zh|en)(?P=lq))"""
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
    return [
        m
        for m in re.findall(r"[^\"'\s>]*sitemap\.xml", html, flags=re.IGNORECASE)
        if not re.fullmatch(
            r"https://leetcode\.doocs\.org(?:/en)?/sitemap\.xml", m, flags=re.I
        )
        and m not in ("/sitemap.xml", "/en/sitemap.xml")
    ]


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
                "zh": "https://leetcode.doocs.org/",
                "en": "https://leetcode.doocs.org/en/",
            },
        )
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])
        self.assertEqual(_page_sitemaps(out), [])

    def test_bilingual_en_page(self):
        out = self._run("lc/100/", EN_CONFIG)
        self.assertEqual(_hrefs(out, "a"), {"zh": "../../../lc/100/", "en": "./"})
        self.assertEqual(
            _hrefs(out, "link"),
            {
                "zh": "https://leetcode.doocs.org/",
                "en": "https://leetcode.doocs.org/en/",
            },
        )
        self.assertEqual(
            _sitemap_hrefs(out), ["https://leetcode.doocs.org/en/sitemap.xml"]
        )
        self.assertEqual(_page_sitemaps(out), [])

    def test_lcof_has_no_en_problem(self):
        out = self._run("lcof/3/", CN_CONFIG)
        self.assertEqual(_hrefs(out, "a"), {"zh": "./", "en": "../../en/"})
        self.assertEqual(
            _hrefs(out, "link"),
            {
                "zh": "https://leetcode.doocs.org/",
                "en": "https://leetcode.doocs.org/en/",
            },
        )
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])
        self.assertEqual(_page_sitemaps(out), [])
        self.assertNotIn("/lcof/3/sitemap.xml", out)
        self.assertNotIn("./sitemap.xml", out)

    def test_lcof2_has_no_en_problem(self):
        out = self._run("lcof2/76/", CN_CONFIG)
        self.assertEqual(_hrefs(out, "a")["en"], "../../en/")
        self.assertEqual(_hrefs(out, "link")["en"], "https://leetcode.doocs.org/en/")

    def test_minified_html(self):
        out = self._run("lc/100/", CN_CONFIG, MINIFIED)
        self.assertEqual(_hrefs(out, "a")["en"], "../../en/lc/100/")
        self.assertEqual(_hrefs(out, "link")["en"], "https://leetcode.doocs.org/en/")
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])

    def test_does_not_double_inject_sitemap(self):
        html = HTML.replace(
            "</head>",
            '<link rel="sitemap" href="https://leetcode.doocs.org/sitemap.xml"></head>',
        )
        out = self._run("lc/100/", CN_CONFIG, html)
        self.assertEqual(_sitemap_hrefs(out), ["https://leetcode.doocs.org/sitemap.xml"])

    def test_empty_output(self):
        self.assertEqual(sop.on_post_page("", SimpleNamespace(url="lc/100/"), CN_CONFIG), "")

    def test_alternate_without_origin_stays_on_language_root(self):
        out = self._run("lcof/3/", {"site_url": "", "site_dir": "site"})
        self.assertEqual(_hrefs(out, "link"), {"zh": "/", "en": "/en/"})
        self.assertEqual(_sitemap_hrefs(out), ["/sitemap.xml"])
        self.assertEqual(_page_sitemaps(out), [])

    def test_range_index_does_not_emit_page_sitemap(self):
        out = self._run("lc/0100-0199/", CN_CONFIG)
        self.assertEqual(_hrefs(out, "link")["zh"], "https://leetcode.doocs.org/")
        self.assertNotIn("/lc/0100-0199/sitemap.xml", out)
        self.assertEqual(_page_sitemaps(out), [])


if __name__ == "__main__":
    unittest.main()
