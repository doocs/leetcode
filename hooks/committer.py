"""
hooks/committer.py – 使用源码文件 git 时间增量缓存，
支持 URL 编码字符（Two%20Sum → Two Sum）
"""

import fnmatch
import json
import os
import random
import subprocess
import urllib.parse
from datetime import datetime, timezone
from pathlib import Path
from typing import Dict, List

import requests


# ───────────────────────── 日志 ───────────────────────── #
def _now() -> str:
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")


def _log(msg: str, level: str = "INFO"):
    print(f"{_now()}  [{level}] {msg}")


# ───────────────────────── 工具函数 ───────────────────────── #
def _exclude(src_path: str, globs: List[str]) -> bool:
    for g in globs:
        if fnmatch.fnmatchcase(src_path, g):
            return True
        if os.sep != "/":
            if fnmatch.fnmatchcase(src_path.replace(os.sep, "/"), g):
                return True
    return False


def _get_header() -> Dict[str, str]:
    if "MKDOCS_API_KEYS" in os.environ:
        keys = [k.strip() for k in os.environ["MKDOCS_API_KEYS"].split(",") if k.strip()]
        if keys:
            return {"Authorization": "token " + random.choice(keys)}
    return {}


def _file_git_datetime(repo_path: str) -> datetime:
    try:
        ts = subprocess.check_output(
            ["git", "log", "-1", "--format=%ct", "--", repo_path],
            text=True,
            stderr=subprocess.DEVNULL,
        ).strip()
        if ts:
            return datetime.fromtimestamp(int(ts), tz=timezone.utc)
    except Exception:
        pass
    return datetime.now(tz=timezone.utc)


# ───────────────────────── 主插件 ───────────────────────── #
class CommitterPlugin:
    def __init__(self):
        self.cache_path = Path(".cache/plugin/git-committers/page-authors.json")
        self.cache_path.parent.mkdir(parents=True, exist_ok=True)

        self.page_authors: Dict[str, Dict] = {}
        self.last_request_status = 0

    # ── MkDocs 事件 ── #
    def on_pre_build(self, _cfg):
        if self.cache_path.exists():
            try:
                self.page_authors = json.loads(self.cache_path.read_text())["page_authors"]
                _log(f"Loaded committer cache from {self.cache_path}")
            except Exception as e:
                _log(f"Failed to read cache, ignore: {e}", "WARN")

    def on_post_build(self, _cfg):
        out = {
            "cache_date": datetime.now(tz=timezone.utc).isoformat(),
            "page_authors": self.page_authors,
        }
        self.cache_path.write_text(json.dumps(out, ensure_ascii=False, indent=2))
        _log(f"Saved committer cache to {self.cache_path}")

        _log("========= Committer Summary =========")
        for k, v in sorted(self.page_authors.items()):
            _log(f"[SUMMARY] {k}  |  retrieved: {v.get('retrieved', 'N/A')}")
        _log("=====================================")

    def on_page_context(self, context, page, _cfg, _nav):
        if not page.edit_url or _exclude(page.file.src_path, []):
            return context

        repo_path = self._repo_path_from_edit_url(page.edit_url)
        api_url = self._api_url_from_repo_path(repo_path)
        authors = self._get_authors_with_cache(api_url, repo_path)

        context["committers"] = authors
        context["committers_source"] = "github" if authors else "cache"
        return context

    # ── 内部方法 ── #
    @staticmethod
    def _repo_path_from_edit_url(edit_url: str) -> str:
        """
        例：
        edit_url =
        https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0001.Two%20Sum/README.md
        返回：
        solution/0000-0099/0001.Two Sum/README.md
        """
        raw_path = edit_url.split("/edit/main/")[-1]
        return urllib.parse.unquote(raw_path)

    @staticmethod
    def _api_url_from_repo_path(repo_path: str) -> str:
        # 重新进行 URL 编码，确保空格等字符合法
        quoted = urllib.parse.quote(repo_path)
        return (
            "https://api.github.com/repos/doocs/leetcode/commits"
            f"?path={quoted}&sha=main&per_page=100"
        )

    def _get_authors_with_cache(self, api_url: str, repo_path: str) -> List[Dict]:
        git_mtime = _file_git_datetime(repo_path).isoformat()

        cached = self.page_authors.get(repo_path)
        cached_time = cached.get("retrieved") if cached else None

        if cached and cached_time and git_mtime <= cached_time:
            _log(f"[CACHE HIT] {repo_path}  git:{git_mtime}  cache:{cached_time}")
            return cached["authors"]

        _log(f"[CACHE MISS] {repo_path}  git:{git_mtime}  cache:{cached_time}")

        if self.last_request_status in (401, 403):
            _log("Skip API request due to previous 401/403", "WARN")
            return cached["authors"] if cached else []

        authors: List[Dict] = []
        for attempt in range(5):
            try:
                r = requests.get(api_url, headers=_get_header(), timeout=10)
            except Exception as e:
                _log(f"Request error ({attempt+1}/5): {e}", "ERROR")
                continue

            self.last_request_status = r.status_code
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
                break
            elif r.status_code in (401, 403):
                _log(f"GitHub API limit ({r.status_code}); stop further requests", "ERROR")
                return cached["authors"] if cached else []
            else:
                _log(f"Unexpected status {r.status_code}; retrying…", "ERROR")

        self.page_authors[repo_path] = {
            "authors": authors,
            "retrieved": datetime.now(tz=timezone.utc).isoformat(),
        }
        _log(f"[CACHE UPDATE] {repo_path}  new authors: {len(authors)}")
        return authors


# ───────────────────────── MkDocs 适配 ───────────────────────── #
_plugin = CommitterPlugin()


def on_pre_build(config):
    _plugin.on_pre_build(config)


def on_post_build(config):
    _plugin.on_post_build(config)


def on_page_context(context, page, config, nav):
    return _plugin.on_page_context(context, page, config, nav)
