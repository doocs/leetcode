import json
import os
import re

# Injects a page-scoped config blob. The widget itself is a static
# extra_javascript / extra_css pair so it can be turned off without
# deleting files: set extra.ai.enabled to false.

_HEAD_END = re.compile(r"</head>", re.IGNORECASE)
_AI_ASSET = re.compile(
    r"""<(?:script|link)\b[^>]*(?:/ai-assistant\.js|ai-assistant\.css)[^>]*>"""
    r"""(?:\s*</script>)?""",
    re.IGNORECASE,
)

DEFAULT_PROVIDERS = (
    {
        "id": "deepseek",
        "name": "DeepSeek",
        "base_url": "https://api.deepseek.com/v1",
        "models": ["deepseek-chat", "deepseek-reasoner"],
    },
    {
        "id": "qwen",
        "name": "Qwen",
        "base_url": "https://dashscope.aliyuncs.com/compatible-mode/v1",
        "models": ["qwen-plus", "qwen-turbo"],
    },
    {
        "id": "siliconflow",
        "name": "SiliconFlow",
        "base_url": "https://api.siliconflow.cn/v1",
        "models": ["deepseek-ai/DeepSeek-V3.2", "Qwen/Qwen3-8B"],
    },
    {
        "id": "openai",
        "name": "OpenAI",
        "base_url": "https://api.openai.com/v1",
        "models": ["gpt-4.1-mini", "gpt-4o-mini"],
    },
    {
        "id": "ollama",
        "name": "Ollama",
        "base_url": "http://127.0.0.1:11434/v1",
        "models": ["llama3.2", "qwen2.5"],
    },
    {
        "id": "custom",
        "name": "Custom",
        "base_url": "",
        "models": [],
    },
)


def _config_get(config, key, default=None):
    if isinstance(config, dict):
        return config.get(key, default)
    return getattr(config, key, default)


def _extra(config) -> dict:
    extra = _config_get(config, "extra") or {}
    return extra if isinstance(extra, dict) else {}


def is_enabled(config) -> bool:
    ai = _extra(config).get("ai")
    if ai is None:
        return True
    if isinstance(ai, dict):
        return bool(ai.get("enabled", True))
    return bool(ai)


def is_en_site(config) -> bool:
    site_url = str(_config_get(config, "site_url") or "").rstrip("/")
    site_dir = str(_config_get(config, "site_dir") or "").replace("\\", "/").rstrip("/")
    return site_url.endswith("/en") or site_dir.endswith("/en") or site_dir == "en"


def build_config(page, config) -> dict:
    ai = _extra(config).get("ai")
    ai = ai if isinstance(ai, dict) else {}
    providers = ai.get("providers") or DEFAULT_PROVIDERS
    title = getattr(page, "title", None) or ""
    url = (getattr(page, "url", None) or "").strip()
    endpoint = str(ai.get("endpoint") or os.environ.get("DOOCS_AI_ENDPOINT") or "").strip()
    return {
        "lang": "en" if is_en_site(config) else "zh",
        "title": title,
        "url": url,
        "endpoint": endpoint,
        "defaultProvider": ai.get("default_provider") or "deepseek",
        "providers": providers,
        "plugin": True,
    }


def _config_script(payload: dict) -> str:
    body = (
        json.dumps(payload, ensure_ascii=False)
        .replace("<", "\\u003c")
        .replace(">", "\\u003e")
    )
    return (
        '<script type="application/json" id="doocs-ai-config">'
        f"{body}"
        "</script>"
    )


def on_post_page(output, page, config):
    if not output:
        return output
    if not is_enabled(config):
        return _AI_ASSET.sub("", output)
    if 'id="doocs-ai-config"' in output or "id=doocs-ai-config" in output:
        return output
    snippet = _config_script(build_config(page, config))
    if _HEAD_END.search(output):
        return _HEAD_END.sub(f"{snippet}</head>", output, count=1)
    return snippet + output
