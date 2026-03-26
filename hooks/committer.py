"""
hooks/committer.py - Bulk mtime index + parallel API prefetch

Optimizations:
1. on_pre_build: single git log pass builds {file -> last_mtime} index,
   replacing the original per-page subprocess spawn (3000+ pages -> 1 call).
2. on_files: scan all docs frontmatter to extract edit_url,
   parallel-prefetch GitHub API for all cache misses before page processing.
3. on_page_context: pure cache lookup, near-zero overhead.
"""

import fnmatch
import json
import os
import re
import random
import subprocess
import threading
import urllib.parse
from concurrent.futures import ThreadPoolExecutor, as_completed
from datetime import datetime, timezone
from pathlib import Path
from typing import Dict, List, Optional

import requests


# --- Logging ---
def _now() -> str:
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")


def _log(msg: str, level: str = "INFO"):
    print(f"{_now()}  [{level}] {msg}")


def _ts(dt: datetime) -> int:
    return int(dt.timestamp())


# --- Helpers ---
def _exclude(src_path: str, globs: List[str]) -> bool:
    for g in globs:
        if fnmatch.fnmatchcase(src_path, g):
            return True
        if os.sep != "/" and fnmatch.fnmatchcase(src_path.replace(os.sep, "/"), g):
            return True
    return False


def _get_header() -> Dict[str, str]:
    if "MKDOCS_API_KEYS" in os.environ:
        keys = [k.strip() for k in os.environ["MKDOCS_API_KEYS"].split(",") if k.strip()]
        if keys:
            return {"Authorization": "token " + random.choice(keys)}
    return {}


def _extract_edit_url(md_path: str) -> Optional[str]:
    """Extract edit_url from YAML frontmatter (reads first 2 KB only)."""
    try:
        with open(md_path, encoding="utf-8", errors="ignore") as fh:
            content = fh.read(2048)
        m = re.match(r"^---\s*\n(.*?)\n---", content, re.DOTALL)
        if m:
            for line in m.group(1).splitlines():
                if line.startswith("edit_url:"):
                    return line.split(":", 1)[1].strip()
    except Exception:
        pass
    return None


# --- Main plugin ---
class CommitterPlugin:
    def __init__(self):
        self.cache_path = Path(".git-committers-cache.json")
        self.cache_path.parent.mkdir(parents=True, exist_ok=True)
        self.page_authors: Dict[str, Dict] = {}
        self._mtime_index: Dict[str, int] = {}
        self._auth_failed = threading.Event()

    def on_pre_build(self, cfg):
        # 1. Load existing cache
        if self.cache_path.exists():
            try:
                raw = json.loads(self.cache_path.read_text())["page_authors"]
                for k, v in list(raw.items()):
                    if k.startswith("https://"):
                        continue
                    rt = v.get("retrieved")
                    if isinstance(rt, str):
                        try:
                            raw[k]["retrieved"] = _ts(datetime.fromisoformat(rt))
                        except Exception:
                            raw[k].pop("retrieved", None)
                self.page_authors = raw
                _log(f"Loaded committer cache: {len(self.page_authors)} entries")
            except Exception as e:
                _log(f"Cache read error (ignored): {e}", "WARN")

        # 2. Build mtime index in a single git log pass
        self._mtime_index = self._build_mtime_index()

    def on_files(self, files, config):
        """Identify cache misses and pre-fetch GitHub API results in parallel."""
        docs_dir = config.get("docs_dir", "docs")
        misses: List[tuple] = []

        for f in files:
            if not f.src_path.endswith(".md"):
                continue
            md_path = os.path.join(docs_dir, f.src_path)
            edit_url = _extract_edit_url(md_path)
            if not edit_url:
                continue
            repo_path = self._repo_path_from_edit_url(edit_url)
            git_mtime = self._mtime_index.get(repo_path, 0)
            cached = self.page_authors.get(repo_path)
            cached_time = cached.get("retrieved") if cached else None
            if not (cached and cached_time and git_mtime <= cached_time):
                misses.append((repo_path, self._api_url_from_repo_path(repo_path)))

        if misses:
            _log(f"Pre-fetching {len(misses)} cache misses in parallel (workers=8)...")
            results: Dict[str, List] = {}
            with ThreadPoolExecutor(max_workers=8) as pool:
                future_to_path = {
                    pool.submit(self._fetch_authors_from_api, api_url): repo_path
                    for repo_path, api_url in misses
                }
                done = 0
                for fut in as_completed(future_to_path):
                    repo_path = future_to_path[fut]
                    try:
                        authors = fut.result()
                        if authors is not None:
                            results[repo_path] = authors
                    except Exception as e:
                        _log(f"Pre-fetch failed [{repo_path}]: {e}", "WARN")
                    done += 1
                    if done % 100 == 0:
                        _log(f"  Pre-fetch progress: {done}/{len(misses)}")

            now_ts = _ts(datetime.now(tz=timezone.utc))
            for repo_path, authors in results.items():
                self.page_authors[repo_path] = {"authors": authors, "retrieved": now_ts}
            _log(f"Pre-fetch complete: {len(results)}/{len(misses)} updated.")

        return files

    def on_post_build(self, cfg):
        out = {
            "cache_date": _ts(datetime.now(tz=timezone.utc)),
            "page_authors": self.page_authors,
        }
        self.cache_path.write_text(json.dumps(out, ensure_ascii=False, indent=2))
        _log(f"Saved committer cache: {len(self.page_authors)} entries -> {self.cache_path}")

        _log("========= Committer Summary =========")
        for k, v in sorted(self.page_authors.items()):
            rt = v.get("retrieved", "N/A")
            if isinstance(rt, int):
                rt = datetime.fromtimestamp(rt, tz=timezone.utc).isoformat()
            _log(f"[SUMMARY] {k}  |  retrieved: {rt}")
        _log("=====================================")

    def on_page_context(self, context, page, cfg, nav):
        if not page.edit_url or _exclude(page.file.src_path, []):
            return context

        repo_path = self._repo_path_from_edit_url(page.edit_url)
        git_mtime = self._mtime_index.get(repo_path, 0)
        cached = self.page_authors.get(repo_path)
        cached_time = cached.get("retrieved") if cached else None

        if cached and cached_time and git_mtime <= cached_time:
            authors = cached["authors"]
        else:
            # Fallback: should not be reached after on_files pre-fetch
            _log(f"[FALLBACK FETCH] {repo_path}", "WARN")
            api_url = self._api_url_from_repo_path(repo_path)
            authors = self._fetch_authors_from_api(api_url) or []
            self.page_authors[repo_path] = {
                "authors": authors,
                "retrieved": _ts(datetime.now(tz=timezone.utc)),
            }

        context["committers"] = authors
        context["committers_source"] = "github" if authors else "cache"
        return context

    # --- Internal ---

    def _build_mtime_index(self) -> Dict[str, int]:
        """
        Run git log once and build {file_path -> last_commit_ts} dict.
        Format: each commit starts with "COMMIT <ts>", followed by changed files.
        Since git log outputs newest-first, the first occurrence of each file
        is its most recent commit timestamp.
        """
        _log("Building mtime index via single git log pass...")
        try:
            result = subprocess.run(
                [
                    "git", "log",
                    "--format=COMMIT %ct",
                    "--name-only",
                    "--no-merges",
                    "--diff-filter=ACMR",
                ],
                text=True,
                capture_output=True,
                check=False,
                timeout=120,
            )
            index: Dict[str, int] = {}
            current_ts = 0
            for line in result.stdout.splitlines():
                line = line.strip()
                if not line:
                    continue
                if line.startswith("COMMIT "):
                    current_ts = int(line[7:])
                elif current_ts and line not in index:
                    index[line] = current_ts
            _log(f"Mtime index ready: {len(index)} files indexed.")
            return index
        except Exception as e:
            _log(f"Mtime index build failed: {e}", "ERROR")
            return {}

    def _fetch_authors_from_api(self, api_url: str) -> Optional[List[Dict]]:
        """Fetch commit authors from GitHub API. Returns None on auth error."""
        if self._auth_failed.is_set():
            return None
        authors: List[Dict] = []
        for attempt in range(3):
            try:
                r = requests.get(api_url, headers=_get_header(), timeout=10)
            except Exception as e:
                _log(f"Request error (attempt {attempt + 1}/3): {e}", "ERROR")
                continue
            if r.status_code == 200:
                for commit in r.json():
                    author = commit.get("author") or {}
                    login = author.get("login")
                    if login and login not in {a["login"] for a in authors}:
                        authors.append(
                            {
                                "login": login,
                                "name": login,
                                "url": author.get("html_url"),
                                "avatar": author.get("avatar_url"),
                            }
                        )
                return authors
            elif r.status_code in (401, 403):
                _log(f"GitHub API auth error {r.status_code}; halting further calls", "ERROR")
                self._auth_failed.set()
                return None
            else:
                _log(f"Unexpected status {r.status_code}; retrying...", "WARN")
        return authors

    @staticmethod
    def _repo_path_from_edit_url(edit_url: str) -> str:
        raw = edit_url.split("/edit/main/")[-1]
        return urllib.parse.unquote(raw)

    @staticmethod
    def _api_url_from_repo_path(repo_path: str) -> str:
        quoted = urllib.parse.quote(repo_path)
        return (
            "https://api.github.com/repos/doocs/leetcode/commits"
            f"?path={quoted}&sha=main&per_page=100"
        )


# --- MkDocs hook entry points ---
_plugin = CommitterPlugin()


def on_pre_build(config):
    _plugin.on_pre_build(config)


def on_files(files, config):
    return _plugin.on_files(files, config)


def on_post_build(config):
    _plugin.on_post_build(config)


def on_page_context(context, page, config, nav):
    return _plugin.on_page_context(context, page, config, nav)
