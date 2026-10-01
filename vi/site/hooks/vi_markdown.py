"""Vietnamese page rendering; replaces ext_info.py and thinking_block.py on vi.

Reuses the upstream helpers so code tabs, links and badges render exactly like
the zh/en sites, with Vietnamese labels for the badges and the Thinking block.
Untranslated stubs (vi_status: stub) are marked noindex; translations whose
English source changed after review (vi_status: outdated) get a notice.
"""

import re
import sys
from pathlib import Path
from types import SimpleNamespace

try:
    from mkdocs import plugins
except ImportError:  # unittest without site deps

    class plugins:
        @staticmethod
        def event_priority(_priority):
            return lambda fn: fn


_HOOKS_DIR = Path(__file__).resolve().parent
if str(_HOOKS_DIR) not in sys.path:
    sys.path.insert(0, str(_HOOKS_DIR))

import ext_info  # noqa: E402
import thinking_block  # noqa: E402

# MkDocs restores sys.path after loading a hook, so import upstream hooks now.
try:
    import tags as upstream_tags  # noqa: E402  (needs beautifulsoup4)
except ImportError:  # unittest without site deps
    upstream_tags = None

THINKING_TITLE = "Tư duy"
DIFFICULTY = {"Easy": "Dễ", "Medium": "Trung bình", "Hard": "Khó"}
_VI_LABEL = re.compile(r"^(\s*>\s*)\*\*Tư duy\*\*\s*$", re.M)
_H1 = re.compile(r"^# .+?$", re.M)
_HEAD_END = re.compile(r"</head>", re.IGNORECASE)
NOINDEX = '<meta name="robots" content="noindex">'
DEFAULT_UPSTREAM = "https://leetcode.doocs.org"
OUTDATED_NOTICE = (
    '!!! warning "Bản dịch có thể đã cũ"\n\n'
    "    Bản gốc tiếng Anh đã thay đổi sau khi bài này được dịch. "
    "Xem [bản English]({href}) để có nội dung mới nhất."
)


def add_outdated_notice(markdown, page, config):
    if page.meta.get("vi_status") != "outdated":
        return markdown
    match = _H1.search(markdown)
    if not match:
        return markdown
    extra = (config.get("extra") if isinstance(config, dict) else config.extra) or {}
    upstream = str(extra.get("upstream_site") or DEFAULT_UPSTREAM).rstrip("/")
    href = f"{upstream}/en/{page.url.strip('/')}/"
    notice = OUTDATED_NOTICE.format(href=href)
    end = match.end()
    return markdown[:end] + "\n\n" + notice + "\n\n" + markdown[end:]


def add_difficulty_info(markdown, page):
    meta = page.meta
    badges = []
    if meta.get("source"):
        badges.append(ext_info._badge("Nguồn", meta["source"]))
    if meta.get("difficulty"):
        value = str(meta["difficulty"])
        badges.append(ext_info._badge("Độ khó", DIFFICULTY.get(value, value)))
    if meta.get("rating"):
        badges.append(ext_info._badge("Điểm", meta["rating"]))
    if not badges:
        return markdown
    match = _H1.search(markdown)
    if not match:
        return markdown
    badges_html = f'<p class="lc-badges">{"".join(badges)}</p>'
    end = match.end()
    return markdown[:end] + "\n\n" + badges_html + "\n\n" + markdown[end:]


def convert_thinking_blocks(markdown):
    def repl(match):
        body = _VI_LABEL.sub(r"\1**Thinking**", match.group(1))
        _title, content = thinking_block._strip_quote(body)
        indented = "\n".join(
            f"    {line}" if line.strip() else "" for line in content.splitlines()
        )
        return f'!!! thinking "{THINKING_TITLE}"\n\n{indented}\n'

    return thinking_block.BLOCK_RE.sub(repl, markdown)


@plugins.event_priority(90)
def on_page_markdown(markdown, page, config, files):
    markdown = ext_info.remove_version_switch(markdown)
    markdown = ext_info.rewrite_repo_problem_links(markdown, page)
    markdown = ext_info.strip_images_without_src(markdown)
    markdown = add_outdated_notice(markdown, page, config)
    markdown = add_difficulty_info(markdown, page)
    markdown = ext_info.modify_code_block(markdown)
    return convert_thinking_blocks(markdown)


def on_post_page(output, page, config):
    if not output:
        return output
    if page.meta.get("vi_status") == "stub":
        return _HEAD_END.sub(f"{NOINDEX}</head>", output, count=1)
    if page.url == "tags/" and upstream_tags is not None:
        # upstream tags.py sorts tag lists only for /tags/ and /en/tags/
        page_like = SimpleNamespace(abs_url="/tags/")
        return upstream_tags.on_post_page(output, page_like, config)
    return output
