import json
import sys
import unittest
from pathlib import Path
from types import SimpleNamespace

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "hooks"))
import ai_assistant as ai  # noqa: E402

PAGE = SimpleNamespace(url="lc/1/", title="1. 两数之和")
HEAD = "<html><head><title>x</title></head><body>hi</body></html>"
ASSETS = (
    '<script src="../javascripts/ai-assistant.js"></script>'
    '<link rel="stylesheet" href="../stylesheets/ai-assistant.css">'
)
MINIFIED = (
    "<script src=../javascripts/ai-assistant.js></script>"
    "<link rel=stylesheet href=../stylesheets/ai-assistant.css>"
)


class AiAssistantHookTest(unittest.TestCase):
    def test_enabled_by_default(self):
        self.assertTrue(ai.is_enabled({}))
        self.assertTrue(ai.is_enabled({"extra": {}}))
        self.assertTrue(ai.is_enabled({"extra": {"ai": {}}}))

    def test_can_disable(self):
        self.assertFalse(ai.is_enabled({"extra": {"ai": {"enabled": False}}}))
        self.assertFalse(ai.is_enabled({"extra": {"ai": False}}))

    def test_injects_config_when_enabled(self):
        out = ai.on_post_page(HEAD, PAGE, {"site_url": "https://leetcode.doocs.org"})
        self.assertIn('id="doocs-ai-config"', out)
        raw = out.split('id="doocs-ai-config">', 1)[1].split("</script>", 1)[0]
        payload = json.loads(raw)
        self.assertEqual(payload["lang"], "zh")
        self.assertEqual(payload["title"], "1. 两数之和")
        self.assertEqual(payload["url"], "lc/1/")
        self.assertEqual(payload["defaultProvider"], "deepseek")
        self.assertTrue(payload["providers"])

    def test_en_site_uses_en_lang(self):
        out = ai.on_post_page(
            HEAD,
            PAGE,
            {"site_url": "https://leetcode.doocs.org/en", "site_dir": "site/en"},
        )
        raw = out.split('id="doocs-ai-config">', 1)[1].split("</script>", 1)[0]
        self.assertEqual(json.loads(raw)["lang"], "en")

    def test_does_not_double_inject(self):
        first = ai.on_post_page(HEAD, PAGE, {})
        second = ai.on_post_page(first, PAGE, {})
        self.assertEqual(first.count("doocs-ai-config"), 1)
        self.assertEqual(second.count("doocs-ai-config"), 1)

    def test_disabled_strips_assets(self):
        html = HEAD.replace("</head>", ASSETS + "</head>")
        out = ai.on_post_page(html, PAGE, {"extra": {"ai": {"enabled": False}}})
        self.assertNotIn("ai-assistant.js", out)
        self.assertNotIn("ai-assistant.css", out)
        self.assertNotIn("doocs-ai-config", out)
        self.assertIn("hi", out)

    def test_disabled_strips_minified_assets(self):
        html = "<html><head>" + MINIFIED + "</head><body></body></html>"
        out = ai.on_post_page(html, PAGE, {"extra": {"ai": False}})
        self.assertNotIn("ai-assistant", out)

    def test_empty_output(self):
        self.assertEqual(ai.on_post_page("", PAGE, {}), "")

    def test_custom_providers(self):
        config = {
            "extra": {
                "ai": {
                    "default_provider": "custom",
                    "providers": [
                        {
                            "id": "custom",
                            "name": "Mine",
                            "base_url": "https://proxy.example/v1",
                            "models": ["demo"],
                        }
                    ],
                }
            }
        }
        payload = ai.build_config(PAGE, config)
        self.assertEqual(payload["defaultProvider"], "custom")
        self.assertEqual(payload["providers"][0]["base_url"], "https://proxy.example/v1")


class AiAssistantAssetsTest(unittest.TestCase):
    def test_cn_and_en_assets_match(self):
        for name in (
            "javascripts/ai-assistant.js",
            "stylesheets/ai-assistant.css",
        ):
            cn = (ROOT / "docs" / name).read_text(encoding="utf-8")
            en = (ROOT / "docs-en" / name).read_text(encoding="utf-8")
            self.assertEqual(cn, en)
            self.assertTrue(cn.strip())


if __name__ == "__main__":
    unittest.main()
