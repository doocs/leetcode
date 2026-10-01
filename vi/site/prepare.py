"""Prepare the build directory for the fork's site (deployed by Vercel).

Overlays the pinned upstream site engine (doocs/leetcode, docs branch), runs the
upstream build_site.py for the original zh/en pages (unchanged), generates the
Vietnamese tree with build_vi.py and writes one MkDocs config per site:

    mkdocs-site-zh.yml  ->  site/       (中文, upstream content)
    mkdocs-site-en.yml  ->  site/en/    (English, upstream content)
    mkdocs-site-vi.yml  ->  site/vi/    (Tiếng Việt)

The zh/en configs inherit the upstream ones and only change hosting settings:
site URL, repository link, the language selector, fork hooks and analytics.

Examples:

    python3 vi/site/prepare.py --only 1,2,lcci/01.01 --no-minify
    python3 vi/site/prepare.py --engine .preview/vi-engine \\
        --site-url https://leetcode-vi.example.com --repo owner/leetcode
    python3 vi/site/prepare.py --export-engine .preview/vi-engine

The three sites are served from the root of one domain (see vercel.json).
"""

from __future__ import annotations

import argparse
import os
import re
import shutil
import subprocess
import sys
import tarfile
from io import BytesIO
from pathlib import Path
from typing import Dict, List, Optional, Set

import yaml

HERE = Path(__file__).resolve().parent
REPO = HERE.parents[1]
sys.path.insert(0, str(HERE))

import build_vi  # noqa: E402

ENGINE_URL = "https://github.com/doocs/leetcode"
ENGINE_ITEMS = (
    "docs",
    "docs-en",
    "hooks",
    "overrides",
    "mkdocs.yml",
    "mkdocs-en.yml",
    "build_site.py",
    "requirements.txt",
    ".git-committers-cache.json",
)
REQUIRED_ENGINE_ITEMS = ENGINE_ITEMS[:-1]
SERIES_ROOTS = ("solution", "lcof", "lcof2", "lcci", "lcp", "lcs")
WORKDIR_MARK = ".vi-site-workdir"
ENGINE_MARK = ".vi-engine-export"
DEFAULT_WORKDIR = REPO / ".preview" / "vi-site"
DEFAULT_SITE_URL = "http://127.0.0.1:8000"
DEFAULT_REPO = "vandunxg/leetcode"

FORK_HOOKS = ["hooks/fork_site.py", "hooks/vi_switch.py"]
# Upstream hooks replaced on the vi site: committer.py queries doocs/leetcode
# history, ext_info.py/thinking_block.py emit zh/en labels (vi_markdown.py
# reuses their helpers) and stay_on_page.py only knows zh/en (vi_switch.py).
VI_DROPPED_HOOKS = {
    "committer.py",
    "ext_info.py",
    "thinking_block.py",
    "stay_on_page.py",
}
VI_HOOKS = ["hooks/vi_markdown.py"]
# Untranslated problems render with overrides/vi_stub.html and stay out of the nav.
VI_NOT_IN_NAV = "/lc/*.md\n/lcci/*.md\n"

ALTERNATE = [
    {"name": "English", "link": "/en/", "lang": "en"},
    {"name": "中文", "link": "/", "lang": "zh"},
    {"name": "Tiếng Việt", "link": "/vi/", "lang": "vi"},
]
VI_DESCRIPTION = (
    "Lời giải LeetCode và Cracking the Coding Interview bằng nhiều ngôn ngữ "
    "lập trình — bản tiếng Việt"
)
VI_COPYRIGHT = (
    'Copyright &copy; 2026 <a href="https://github.com/doocs">Doocs</a> · '
    'Bản dịch tiếng Việt: <a href="https://github.com/{repo}">{repo}</a><br>'
    'Nội dung được cấp phép theo <a rel="license" '
    'href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons '
    "Attribution-ShareAlike 4.0 International</a>."
)


# --- engine -----------------------------------------------------------------


def git(*args: str, **kwargs) -> subprocess.CompletedProcess:
    return subprocess.run(["git", *args], cwd=str(REPO), check=False, **kwargs)


def git_ok(*args: str) -> bool:
    quiet = {"stdout": subprocess.DEVNULL, "stderr": subprocess.DEVNULL}
    return git(*args, **quiet).returncode == 0


def engine_ref() -> str:
    for line in (HERE / "ENGINE_REF").read_text(encoding="utf-8").splitlines():
        if re.fullmatch(r"[0-9a-f]{40}", line.strip()):
            return line.strip()
    sys.exit("vi/site/ENGINE_REF has no 40-character commit SHA")


def extract_engine(ref: str, dest: Path) -> None:
    if not git_ok("cat-file", "-e", f"{ref}^{{commit}}"):
        print(f"Fetching site engine {ref} from {ENGINE_URL}")
        if git("fetch", "--depth", "1", ENGINE_URL, ref).returncode != 0:
            sys.exit(f"Cannot fetch {ref} from {ENGINE_URL}")
    have = [item for item in ENGINE_ITEMS if git_ok("cat-file", "-e", f"{ref}:{item}")]
    p = git("archive", "--format=tar", ref, *have, capture_output=True)
    if p.returncode != 0:
        sys.exit(p.stderr.decode("utf-8", errors="replace") or "git archive failed")
    dest.mkdir(parents=True, exist_ok=True)
    with tarfile.open(fileobj=BytesIO(p.stdout), mode="r:") as tar:
        tar.extractall(dest, filter="data")


def copy_engine(src: Path, dest: Path) -> None:
    for item in ENGINE_ITEMS:
        path = src / item
        if path.is_dir():
            shutil.copytree(path, dest / item)
        elif path.is_file():
            shutil.copy2(path, dest / item)


def check_engine(dest: Path) -> None:
    missing = [item for item in REQUIRED_ENGINE_ITEMS if not (dest / item).exists()]
    if missing:
        sys.exit("Site engine is missing: " + ", ".join(missing))


# --- work directory ---------------------------------------------------------


def reset_workdir(path: Path) -> None:
    path = path.resolve()
    if path == REPO or path in REPO.parents or path == HERE:
        sys.exit(f"Refusing to use {path} as the work directory")
    if path.exists() and any(path.iterdir()) and not (path / WORKDIR_MARK).exists():
        sys.exit(f"{path} is not empty and was not created by prepare.py")
    if path.exists():
        for child in path.iterdir():
            if child.is_symlink() or child.is_file():
                child.unlink()
            else:
                shutil.rmtree(child)
    path.mkdir(parents=True, exist_ok=True)
    (path / WORKDIR_MARK).write_text("", encoding="utf-8")


def link_content(workdir: Path) -> None:
    for root in SERIES_ROOTS:
        src = REPO / root
        if not src.is_dir():
            continue
        try:
            os.symlink(src, workdir / root, target_is_directory=True)
        except OSError:
            shutil.copytree(src, workdir / root)


def write_contest_pages(workdir: Path) -> None:
    pages = (
        ("CONTEST_README.md", "docs", "# 力扣竞赛"),
        ("CONTEST_README_EN.md", "docs-en", "# LeetCode Contest"),
    )
    for name, docs_dir, heading in pages:
        src = REPO / "solution" / name
        dst = workdir / docs_dir / "contest.md"
        if src.is_file():
            shutil.copy2(src, dst)
        else:
            print(f"warning: solution/{name} missing; writing a stub.")
            dst.write_text(f"---\ncomments: true\n---\n\n{heading}\n", encoding="utf-8")


def problem_dir_for(token: str) -> str:
    """Resolve 1 / lc/1 / lcci/01.01 / a relative path to a problem directory."""
    token = token.strip().strip("/")
    if (REPO / token).is_dir():
        return Path(token).as_posix()
    series, _, ident = token.rpartition("/")
    series = {"": "solution", "lc": "solution"}.get(series, series)
    if series == "solution" and ident.isdigit():
        prefix = f"{int(ident):04d}."
        hits = sorted((REPO / "solution").glob(f"*/{prefix}*"))
    else:
        hits = sorted((REPO / series).glob(f"{ident}.*"))
    hits = [p for p in hits if p.is_dir()]
    if len(hits) != 1:
        sys.exit(f"--only: cannot resolve {token!r} to one problem directory")
    return hits[0].relative_to(REPO).as_posix()


# --- configs ----------------------------------------------------------------


class _TolerantLoader(yaml.SafeLoader):
    """Safe loader that keeps going on custom tags (e.g. !!python/name)."""


_TolerantLoader.add_multi_constructor("!", lambda loader, suffix, node: None)
_TolerantLoader.add_multi_constructor(
    "tag:yaml.org,2002:python/", lambda loader, suffix, node: None
)


def merge(base: dict, override: dict) -> dict:
    out = dict(base)
    for key, value in override.items():
        if isinstance(value, dict) and isinstance(out.get(key), dict):
            out[key] = merge(out[key], value)
        else:
            out[key] = value
    return out


def load_config(path: Path) -> dict:
    """Load a MkDocs config, resolving INHERIT like MkDocs does."""
    data = yaml.load(path.read_text(encoding="utf-8"), Loader=_TolerantLoader) or {}
    parent = data.pop("INHERIT", None)
    if parent:
        return merge(load_config(path.parent / parent), data)
    return data


def site_configs(
    zh: dict, en: dict, site_url: str, repo: str, minify: bool = True
) -> Dict[str, dict]:
    """Per-site config overrides; zh/en inherit the upstream configs."""
    base = site_url.rstrip("/")
    repo_keys = {"repo_name": repo, "repo_url": f"https://github.com/{repo}"}
    hooks = list(zh.get("hooks") or [])
    vi_hooks = [h for h in hooks if Path(h).name not in VI_DROPPED_HOOKS]

    def extra(lang: str) -> dict:
        return {"site_lang": lang, "alternate": ALTERNATE, "analytics": None}

    configs = {
        "zh": {
            "INHERIT": "mkdocs.yml",
            "site_url": base,
            **repo_keys,
            "hooks": hooks + FORK_HOOKS,
            "extra": extra("zh"),
        },
        "en": {
            "INHERIT": "mkdocs-en.yml",
            "site_url": f"{base}/en",
            **repo_keys,
            "hooks": list(en.get("hooks") or hooks) + FORK_HOOKS,
            "extra": extra("en"),
        },
        "vi": {
            "INHERIT": "mkdocs.yml",
            "site_url": f"{base}/vi",
            "site_description": VI_DESCRIPTION,
            "site_dir": "site/vi",
            "docs_dir": build_vi.DOCS_VI,
            **repo_keys,
            "copyright": VI_COPYRIGHT.format(repo=repo),
            "theme": {"language": "vi"},
            "hooks": vi_hooks + VI_HOOKS + FORK_HOOKS,
            "not_in_nav": VI_NOT_IN_NAV,
            "extra": extra("vi"),
        },
    }
    if not minify:
        for lang, parent in (("zh", zh), ("en", en), ("vi", zh)):
            plugins = parent.get("plugins") or []
            configs[lang]["plugins"] = [
                p for p in plugins if not (isinstance(p, dict) and "minify" in p)
            ]
    return configs


def write_configs(workdir: Path, configs: Dict[str, dict], vi_nav: str) -> None:
    for lang, data in configs.items():
        text = yaml.safe_dump(data, allow_unicode=True, sort_keys=False, width=1000)
        if lang == "vi":
            text += "\n" + vi_nav
        (workdir / f"mkdocs-site-{lang}.yml").write_text(text, encoding="utf-8")


# --- main -------------------------------------------------------------------


def prepare(
    workdir: Path,
    engine: Optional[Path],
    site_url: str,
    repo: str,
    only: Optional[List[str]],
    minify: bool,
) -> None:
    reset_workdir(workdir)
    if engine:
        copy_engine(engine, workdir)
    else:
        extract_engine(engine_ref(), workdir)
    check_engine(workdir)
    link_content(workdir)
    write_contest_pages(workdir)

    only_dirs: Optional[Set[str]] = None
    env = os.environ.copy()
    if only:
        only_dirs = {problem_dir_for(token) for token in only}
        listing = workdir / "only-dirs.txt"
        listing.write_text("\n".join(sorted(only_dirs)) + "\n", encoding="utf-8")
        env["PREVIEW_ONLY_DIRS_FILE"] = str(listing)
    subprocess.run(
        [sys.executable, "build_site.py"], cwd=str(workdir), env=env, check=True
    )

    vi_nav = build_vi.build(
        workdir,
        REPO / "vi",
        HERE / "docs-vi",
        repo,
        only=only_dirs,
        reports_dir=REPO / "translation" / "state" / "units",
    )
    for hook in sorted((HERE / "hooks").glob("*.py")):
        shutil.copy2(hook, workdir / "hooks" / hook.name)
    for template in sorted((HERE / "overrides").glob("*.html")):
        shutil.copy2(template, workdir / "overrides" / template.name)

    zh = load_config(workdir / "mkdocs.yml")
    en = load_config(workdir / "mkdocs-en.yml")
    write_configs(workdir, site_configs(zh, en, site_url, repo, minify), vi_nav)
    print(f"Prepared {workdir}")


def main() -> None:
    parser = argparse.ArgumentParser(
        description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter
    )
    parser.add_argument("--workdir", type=Path, default=DEFAULT_WORKDIR)
    parser.add_argument(
        "--engine",
        type=Path,
        help="checkout of the upstream docs branch (default: extract ENGINE_REF)",
    )
    parser.add_argument("--site-url", default=DEFAULT_SITE_URL)
    parser.add_argument("--repo", default=DEFAULT_REPO, help="owner/name for links")
    parser.add_argument(
        "--only", help="comma-separated problems for a quick preview, e.g. 1,lcci/01.01"
    )
    parser.add_argument("--no-minify", action="store_true")
    parser.add_argument(
        "--export-engine",
        type=Path,
        metavar="DIR",
        help="only extract the pinned engine into DIR (used by the hook tests)",
    )
    args = parser.parse_args()

    if args.export_engine:
        dest = args.export_engine
        if dest.exists() and any(dest.iterdir()):
            if not (dest / ENGINE_MARK).is_file() or (dest / ".git").exists():
                sys.exit(f"{dest} is not empty and was not created by --export-engine")
            shutil.rmtree(dest)
        extract_engine(engine_ref(), dest)
        check_engine(dest)
        (dest / ENGINE_MARK).write_text(engine_ref() + "\n", encoding="utf-8")
        print(f"Exported site engine {engine_ref()} to {args.export_engine}")
        return
    only = [t for t in (args.only or "").split(",") if t.strip()] or None
    prepare(
        args.workdir, args.engine, args.site_url, args.repo, only, not args.no_minify
    )


if __name__ == "__main__":
    main()
