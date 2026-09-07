import sys
import unittest
from pathlib import Path
from types import SimpleNamespace

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import conditional_mathjax as cmj  # noqa: E402

PAGE = SimpleNamespace(url="lc/1/")
CONFIG = {}

MATHJAX_SCRIPTS = (
    '<script src="../javascripts/mathjax.js"></script>'
    '<script src="https://cdn-doocs.oss-cn-shenzhen.aliyuncs.com/npm/mathjax@3/es5/tex-mml-chtml.js"></script>'
)
MINIFIED_SCRIPTS = (
    "<script src=../javascripts/mathjax.js></script>"
    "<script src=https://cdn-doocs.oss-cn-shenzhen.aliyuncs.com/npm/mathjax@3/es5/tex-mml-chtml.js></script>"
)
CONFIG_ONLY = (
    "<script>window.MathJax={options:{processHtmlClass:'arithmatex'}}</script>"
    + MATHJAX_SCRIPTS
)


class ConditionalMathjaxTest(unittest.TestCase):
    def test_keeps_scripts_when_page_has_formula(self):
        html = f'<p><span class="arithmatex">\\(x\\)</span></p>{MATHJAX_SCRIPTS}'
        self.assertEqual(cmj.on_post_page(html, PAGE, CONFIG), html)

    def test_strips_scripts_when_page_has_no_formula(self):
        html = f"<h1>两数之和</h1>{MATHJAX_SCRIPTS}"
        out = cmj.on_post_page(html, PAGE, CONFIG)
        self.assertNotIn("mathjax.js", out)
        self.assertNotIn("tex-mml-chtml.js", out)
        self.assertIn("两数之和", out)

    def test_strips_minified_scripts(self):
        html = f"<p>plain</p>{MINIFIED_SCRIPTS}"
        out = cmj.on_post_page(html, PAGE, CONFIG)
        self.assertNotIn("mathjax.js", out)
        self.assertNotIn("tex-mml-chtml.js", out)

    def test_ignores_arithmatex_in_mathjax_config(self):
        out = cmj.on_post_page(CONFIG_ONLY, PAGE, CONFIG)
        self.assertNotIn("mathjax.js", out)
        self.assertNotIn("tex-mml-chtml.js", out)
        self.assertIn("processHtmlClass", out)

    def test_empty_output(self):
        self.assertEqual(cmj.on_post_page("", PAGE, CONFIG), "")


if __name__ == "__main__":
    unittest.main()
