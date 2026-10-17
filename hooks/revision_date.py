"""Set page.update_date from the main-branch source file's last commit.

Flattened pages written by build_site.py are untracked, so MkDocs would
otherwise stamp every sitemap lastmod with the build date. The deploy build
checks out main with full history before mkdocs runs.
"""

from __future__ import annotations

import re
import subprocess
import sys
from pathlib import Path
from typing import Dict, Iterable, Optional, Set

_HOOKS_DIR = Path(__file__).resolve().parent
if str(_HOOKS_DIR) not in sys.path:
    sys.path.insert(0, str(_HOOKS_DIR))

import edit_map  # noqa: E402

_DATE = re.compile(r"^(\d{4}-\d{2}-\d{2})T")
_LC_NUM = re.compile(r"^lc/(\d+)\.md$")
_CACHE: Dict[str, Dict[str, str]] = {}


def reset_cache() -> None:
    _CACHE.clear()


def commit_dates_from_log(lines: Iterable[str], pending: Set[str]) -> Dict[str, str]:
    found: Dict[str, str] = {}
    current: Optional[str] = None
    wanted = {p.replace("\\", "/") for p in pending if p}
    if not wanted:
        return found
    for raw in lines:
        line = raw.rstrip("\r\n")
        if not line:
            continue
        matched = _DATE.match(line)
        if matched:
            current = matched.group(1)
            continue
        if current is None or line not in wanted or line in found:
            continue
        found[line] = current
        if len(found) == len(wanted):
            break
    return found


def commit_dates(paths: Set[str]) -> Dict[str, str]:
    pending = {p.replace("\\", "/") for p in paths if p}
    if not pending:
        return {}
    roots = sorted({p.split("/", 1)[0] for p in pending})
    cmd = [
        "git",
        "--no-pager",
        "-c",
        "core.quotepath=false",
        "-c",
        "i18n.logOutputEncoding=utf-8",
        "log",
        "--pretty=format:%cI",
        "--name-only",
        "--",
        *roots,
    ]
    try:
        proc = subprocess.Popen(
            cmd,
            stdout=subprocess.PIPE,
            stderr=subprocess.DEVNULL,
            text=True,
            encoding="utf-8",
            errors="replace",
        )
    except OSError:
        return {}
    try:
        if proc.stdout is None:
            return {}
        return commit_dates_from_log(proc.stdout, pending)
    finally:
        if proc.poll() is None:
            proc.kill()
            proc.wait()
        else:
            proc.wait()


def index_dates(mapping: Dict[str, str], repo_dates: Dict[str, str]) -> Dict[str, str]:
    """Map flattened page paths, and each LeetCode range index, to YYYY-MM-DD."""
    out: Dict[str, str] = {}
    ranges: Dict[str, str] = {}
    for dest, repo in mapping.items():
        dest = dest.replace("\\", "/")
        date = repo_dates.get(repo.replace("\\", "/"))
        if not date:
            continue
        out[dest] = date
        matched = _LC_NUM.match(dest)
        if not matched:
            continue
        start = int(matched.group(1)) // 100 * 100
        key = f"lc/{start:04d}-{start + 99:04d}.md"
        prev = ranges.get(key)
        if prev is None or date > prev:
            ranges[key] = date
    out.update(ranges)
    return out


def _docs_dir(config) -> str:
    if isinstance(config, dict):
        return str(config.get("docs_dir") or "docs")
    return str(config.get("docs_dir", "docs"))


def ensure(docs_dir: str) -> Dict[str, str]:
    cached = _CACHE.get(docs_dir)
    if cached is not None:
        return cached
    mapping = edit_map.load_edit_map(docs_dir)
    built = index_dates(mapping, commit_dates(set(mapping.values())))
    _CACHE[docs_dir] = built
    return built


def on_page_markdown(markdown, page, config, files):
    try:
        src = getattr(getattr(page, "file", None), "src_path", "") or ""
        src = src.replace("\\", "/")
        date = ensure(_docs_dir(config)).get(src)
        if date:
            page.update_date = date
    except Exception as e:
        print(f"Error in revision_date hook: {e}")
    return markdown
