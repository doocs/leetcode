import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import page_speed  # noqa: E402

PAGE = """
<script src=../../assets/javascripts/glightbox.min.js></script>
<script src=../javascripts/mathjax.js></script>
<script src="https://cdn.example/tex-mml-chtml.js"></script>
<script>"undefined"!=typeof __md_analytics&&__md_analytics()</script>
<script id=init-glightbox>const lightbox = GLightbox({});
document$.subscribe(()=>{ lightbox.reload(); });
</script>
"""


class PageSpeedTest(unittest.TestCase):
    def test_defers_blocking_scripts_and_analytics(self):
        out = page_speed.on_post_page(PAGE, None, None)
        self.assertIn("glightbox.min.js defer>", out)
        self.assertIn("mathjax.js defer>", out)
        self.assertIn('tex-mml-chtml.js" defer>', out)
        self.assertIn('window.addEventListener("load",__md_analytics)', out)
        self.assertNotIn("__md_analytics&&__md_analytics()", out)
        self.assertIn(
            'document.addEventListener("DOMContentLoaded",function(){const lightbox',
            out,
        )

    def test_is_idempotent(self):
        once = page_speed.on_post_page(PAGE, None, None)
        twice = page_speed.on_post_page(once, None, None)
        self.assertEqual(once, twice)

    def test_leaves_unrelated_html(self):
        html = "<p>hello</p>"
        self.assertEqual(page_speed.on_post_page(html, None, None), html)
        self.assertIsNone(page_speed.on_post_page(None, None, None))
