"""Resolve flattened MkDocs pages back to their main-branch source paths."""

from __future__ import annotations

import json
from pathlib import Path
from typing import Dict, Optional
from urllib.parse import quote, unquote

EDIT_URL_PREFIX = "https://github.com/doocs/leetcode/edit/main/"
EDIT_MAP_NAME = ".edit_map.json"
_CACHE: Dict[str, Dict[str, str]] = {}


def reset_cache() -> None:
    _CACHE.clear()


def load_edit_map(docs_dir: str) -> Dict[str, str]:
    key = str(docs_dir)
    cached = _CACHE.get(key)
    if cached is not None:
        return cached
    path = Path(docs_dir) / EDIT_MAP_NAME
    if not path.is_file():
        _CACHE[key] = {}
        return _CACHE[key]
    data = json.loads(path.read_text(encoding="utf-8"))
    _CACHE[key] = {str(k).replace("\\", "/"): str(v) for k, v in data.items()}
    return _CACHE[key]


def repo_path_for(src_path: str, docs_dir: str) -> Optional[str]:
    dest = (src_path or "").replace("\\", "/")
    return load_edit_map(docs_dir).get(dest)


def edit_url_for(repo_path: str) -> str:
    return EDIT_URL_PREFIX + quote(repo_path, safe="/")


def repo_path_from_edit_url(edit_url: str) -> str:
    return unquote(edit_url.split("/edit/main/")[-1])


def resolve_edit_url(
    src_path: str, docs_dir: str, meta: Optional[dict] = None
) -> Optional[str]:
    repo_path = repo_path_for(src_path, docs_dir)
    if repo_path:
        return edit_url_for(repo_path)
    page_edit_url = (meta or {}).get("edit_url")
    if page_edit_url:
        return str(page_edit_url)
    return None
