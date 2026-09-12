import sys
from pathlib import Path

from mkdocs import plugins

_HOOKS_DIR = Path(__file__).resolve().parent
if str(_HOOKS_DIR) not in sys.path:
    sys.path.insert(0, str(_HOOKS_DIR))

import edit_map  # noqa: E402

# https://www.mkdocs.org/dev-guide/plugins/#events


@plugins.event_priority(100)
def on_page_markdown(markdown, page, config, files):
    src_path = getattr(getattr(page, "file", None), "src_path", "") or ""
    docs_dir = config.get("docs_dir", "docs")
    page.edit_url = edit_map.resolve_edit_url(src_path, docs_dir, page.meta)
    return markdown
