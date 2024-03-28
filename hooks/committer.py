import fnmatch
import json
import os
import random
from datetime import datetime
from typing import List

import requests


def exclude(src_path: str, globs: List[str]) -> bool:
    """
    Determine if a src_path should be excluded.
    Supports globs (e.g. folder/* or *.md).
    Credits: code adapted from
    https://github.com/timvink/mkdocs-git-authors-plugin/blob/master/mkdocs_git_authors_plugin/exclude.py
    Args:
        src_path (src): Path of file
        globs (list): list of globs
    Returns:
        (bool): whether src_path should be excluded
    """
    assert isinstance(src_path, str)
    assert isinstance(globs, list)

    for g in globs:
        if fnmatch.fnmatchcase(src_path, g):
            return True

        # Windows reports filenames as eg.  a\\b\\c instead of a/b/c.
        # To make the same globs/regexes match filenames on Windows and
        # other OSes, let's try matching against converted filenames.
        # On the other hand, Unix actually allows filenames to contain
        # literal \\ characters (although it is rare), so we won't
        # always convert them.  We only convert if os.sep reports
        # something unusual.  Conversely, some future mkdocs might
        # report Windows filenames using / separators regardless of
        # os.sep, so we *always* test with / above.
        if os.sep != "/":
            src_path_fix = src_path.replace(os.sep, "/")
            if fnmatch.fnmatchcase(src_path_fix, g):
                return True
    return False


def get_header() -> dict:
    if "MKDOCS_API_KEYS" in os.environ:
        keys = os.environ["MKDOCS_API_KEYS"].strip().split(",")
        return {"Authorization": "token " + str(random.choice(keys)).strip()}
    return {}


class CommitterPlugin:
    def __init__(self):
        self.cache_dir = ".cache/plugin/git-committers"
        self.cache_file = f"{self.cache_dir}/page-authors.json"
        self.cache_page_authors = {}
        self.cache_date = ""
        self.excluded_pages = []
        self.last_request_return_code = 0

    @staticmethod
    def get_request_url(edit_url: str) -> str:
        path = requests.utils.quote(
            edit_url.replace("https://github.com/doocs/leetcode/edit/main", "").replace(
                "%20", " "
            )
        )
        return f"https://api.github.com/repos/doocs/leetcode/commits?path={path}&sha=main&per_page=100"

    def on_pre_build(self, config):
        if os.path.exists(self.cache_file):
            with open(self.cache_file, "r") as f:
                cache = json.loads(f.read())
                self.cache_date = cache["cache_date"]
                self.cache_page_authors = cache["page_authors"]

    def on_post_build(self, config):
        json_data = json.dumps(
            {
                "cache_date": datetime.now().strftime("%Y-%m-%d"),
                "page_authors": self.cache_page_authors,
            }
        )
        os.makedirs(self.cache_dir, exist_ok=True)
        f = open(self.cache_file, "w")
        f.write(json_data)
        f.close()

    def get_contributors_to_file(self, path: str) -> List[dict]:
        # We already got a 401 (unauthorized) or 403 (rate limit) error, so we don't try again
        if self.last_request_return_code in [401, 403]:
            print("Got a 401 or 403 error, not trying again")
            return []

        authors = []
        r = requests.get(url=path, headers=get_header())
        self.last_request_return_code = r.status_code
        if r.status_code == 200:
            # Get login, url and avatar for each author. Ensure no duplicates.
            res = r.json()
            for commit in res:
                if (
                    commit["author"]
                    and commit["author"]["login"]
                    and commit["author"]["login"]
                    not in [author["login"] for author in authors]
                ):
                    authors.append(
                        {
                            "login": commit["author"]["login"],
                            "name": commit["author"]["login"],
                            "url": commit["author"]["html_url"],
                            "avatar": commit["author"]["avatar_url"],
                        }
                    )
            return authors
        return []

    def list_contributors(self, path: str) -> List[dict]:
        path = path.replace("\\", "/")
        authors = self.get_contributors_to_file(path)
        self.cache_page_authors[path] = {"authors": authors}
        return authors

    def on_page_context(self, context, page, config, nav):
        if not page.edit_url:
            return context
        context["committers"] = []
        if exclude(page.file.src_path, self.excluded_pages):
            return context
        path = self.get_request_url(page.edit_url)
        authors = self.list_contributors(path)
        if authors:
            context["committers"] = authors
            context["committers_source"] = "github"
        return context


plugin = CommitterPlugin()


def on_pre_build(config):
    plugin.on_pre_build(config)


def on_post_build(config):
    plugin.on_post_build(config)


def on_page_context(context, page, config, nav):
    return plugin.on_page_context(context, page, config, nav)
