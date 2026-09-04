"""Local MkDocs preview for a subset of problems.

Copies the docs-branch site engine and selected problem trees into .preview/,
flattens them, then runs mkdocs serve. Re-run after switching the problem set.

Examples:

    node scripts/run-py.js scripts/preview.py --problem 1
    node scripts/run-py.js scripts/preview.py --problem 1,2,42
    node scripts/run-py.js scripts/preview.py --range 1-99
    node scripts/run-py.js scripts/preview.py --changed
    node scripts/run-py.js scripts/preview.py --problem lcof/3 --lang zh
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
from typing import Iterable, List, Optional, Set

REPO = Path(__file__).resolve().parents[1]
PREVIEW = REPO / ".preview"
SERIES_ROOTS = ("solution", "lcof", "lcof2", "lcci", "lcp", "lcs")
DOCS_ITEMS = (
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
DEFAULT_LIMIT = 400


def run(cmd: List[str], **kwargs) -> subprocess.CompletedProcess:
    return subprocess.run(cmd, cwd=str(REPO), check=False, **kwargs)


def git_ok(args: List[str]) -> bool:
    p = run(["git", *args], stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
    return p.returncode == 0


def docs_ref(override: Optional[str] = None) -> str:
    if override:
        if not git_ok(["rev-parse", "--verify", override]):
            sys.exit(f"Unknown git ref {override!r}")
        return override
    for ref in ("docs", "origin/docs", "gh/docs"):
        if git_ok(["rev-parse", "--verify", ref]):
            return ref
    sys.exit(
        "No docs branch found. Fetch it first, e.g.\n"
        "  git fetch origin docs:docs\n"
        "  git fetch gh docs:docs"
    )


def iter_problem_dirs() -> Iterable[Path]:
    for root in SERIES_ROOTS:
        base = REPO / root
        if not base.is_dir():
            continue
        if root == "solution":
            for bucket in sorted(base.iterdir()):
                if bucket.is_dir():
                    for child in sorted(bucket.iterdir()):
                        if child.is_dir():
                            yield child
        else:
            for child in sorted(base.iterdir()):
                if child.is_dir():
                    yield child


def lc_number(problem_dir: Path) -> Optional[int]:
    rel = problem_dir.relative_to(REPO)
    if rel.parts[0] != "solution":
        return None
    head = problem_dir.name.split(".", 1)[0]
    return int(head) if head.isdigit() else None


def series_of(problem_dir: Path) -> str:
    return problem_dir.relative_to(REPO).parts[0]


def find_by_lc_nums(nums: Set[int], *, require_all: bool = True) -> List[Path]:
    found = {}
    for d in iter_problem_dirs():
        n = lc_number(d)
        if n is not None and n in nums:
            found[n] = d
    missing = nums - set(found)
    if require_all and missing:
        sys.exit(
            f"No LeetCode folder for problem(s): {', '.join(map(str, sorted(missing)))}"
        )
    if not found:
        sys.exit("No LeetCode folders matched that range.")
    return [found[n] for n in sorted(found)]


def find_by_token(token: str) -> Path:
    token = token.strip().replace("\\", "/")
    if token.isdigit():
        return find_by_lc_nums({int(token)})[0]
    if "/" not in token:
        sys.exit(
            f"Unknown problem id {token!r}. Use a number or series/id (lcof/3, lcci/01.01)."
        )
    series, ident = token.split("/", 1)
    alias = {"lc": "solution", "offer": "lcof"}
    series = alias.get(series, series)
    if series not in SERIES_ROOTS:
        sys.exit(
            f"Unknown series {series!r}. Expected one of: lc, {', '.join(SERIES_ROOTS)}"
        )
    ident_compact = ident.lstrip("0") or "0"
    for d in iter_problem_dirs():
        if series_of(d) != series:
            continue
        n = lc_number(d)
        if n is not None and str(n) == ident_compact:
            return d
        ids = re.findall(
            r"\d+(?:\.\d+)*",
            d.name.replace("剑指 Offer II", "").replace("面试题", ""),
        )
        for raw in ids:
            if raw == ident or (raw.lstrip("0") or "0") == ident_compact:
                return d
        if d.name.startswith(ident + ".") or d.name == ident:
            return d
    sys.exit(f"No folder matched {token!r}")


def changed_dirs() -> List[Path]:
    cmds = [
        ["git", "diff", "--name-only", "HEAD"],
        ["git", "diff", "--name-only", "--cached"],
        ["git", "ls-files", "--others", "--exclude-standard"],
    ]
    names: Set[str] = set()
    for cmd in cmds:
        p = run(cmd, capture_output=True, text=True)
        if p.returncode != 0:
            continue
        names.update(
            line.strip().replace("\\", "/")
            for line in p.stdout.splitlines()
            if line.strip()
        )
    dirs: Set[Path] = set()
    for name in names:
        parts = Path(name).parts
        if not parts or parts[0] not in SERIES_ROOTS:
            continue
        if parts[0] == "solution" and len(parts) >= 3:
            dirs.add(REPO / parts[0] / parts[1] / parts[2])
        elif len(parts) >= 2:
            dirs.add(REPO / parts[0] / parts[1])
    existing = sorted(d for d in dirs if d.is_dir())
    if not existing:
        sys.exit(
            "No changed problem directories. Pass --problem / --range, or edit a solution first."
        )
    return existing


def parse_range(spec: str) -> Set[int]:
    spec = spec.strip()
    if "-" not in spec:
        sys.exit("--range needs start-end, e.g. 1-99")
    a, b = spec.split("-", 1)
    if not a.isdigit() or not b.isdigit():
        sys.exit(f"Invalid range {spec!r}")
    lo, hi = int(a), int(b)
    if lo > hi:
        lo, hi = hi, lo
    return set(range(lo, hi + 1))


def select_dirs(args: argparse.Namespace) -> List[Path]:
    chosen: List[Path] = []
    seen: Set[Path] = set()

    def add(paths: Iterable[Path]) -> None:
        for p in paths:
            p = p.resolve()
            if p not in seen:
                seen.add(p)
                chosen.append(p)

    if args.changed:
        add(changed_dirs())
    if args.problem:
        for tok in args.problem.split(","):
            tok = tok.strip()
            if tok:
                add([find_by_token(tok)])
    if args.range:
        add(find_by_lc_nums(parse_range(args.range), require_all=False))
    if not chosen:
        sys.exit("Select problems with --problem, --range, and/or --changed.")
    if len(chosen) > args.limit:
        sys.exit(
            f"{len(chosen)} problems selected; over --limit {args.limit}. "
            "Narrow the set or raise --limit."
        )
    return chosen


def extract_docs(dest: Path, ref: Optional[str] = None) -> None:
    ref = docs_ref(ref)
    print(f"Overlay site engine from {ref}")
    p = run(["git", "archive", "--format=tar", ref, *DOCS_ITEMS], capture_output=True)
    if p.returncode != 0:
        # Some items may be missing (cache file). Archive what exists.
        have = []
        for item in DOCS_ITEMS:
            check = run(
                ["git", "cat-file", "-e", f"{ref}:{item}"],
                stdout=subprocess.DEVNULL,
                stderr=subprocess.DEVNULL,
            )
            if check.returncode == 0:
                have.append(item)
        if not have:
            sys.exit(p.stderr.decode("utf-8", errors="replace") or "git archive failed")
        p = run(["git", "archive", "--format=tar", ref, *have], capture_output=True)
        if p.returncode != 0:
            sys.exit(p.stderr.decode("utf-8", errors="replace") or "git archive failed")
    dest.mkdir(parents=True, exist_ok=True)
    with tarfile.open(fileobj=BytesIO(p.stdout), mode="r:") as tar:
        try:
            tar.extractall(dest, filter="data")
        except TypeError:
            tar.extractall(dest)


def wipe_dir(path: Path) -> None:
    if not path.exists():
        return
    for root, dirs, _files in os.walk(path, topdown=False):
        for name in dirs:
            p = Path(root) / name
            if p.is_symlink() or (hasattr(p, "is_junction") and p.is_junction()):
                p.unlink(missing_ok=True)
    shutil.rmtree(path, ignore_errors=True)
    if path.exists():
        shutil.rmtree(path)


def link_or_copy(src: Path, dst: Path) -> None:
    dst.parent.mkdir(parents=True, exist_ok=True)
    if dst.exists() or dst.is_symlink():
        if dst.is_dir() and not dst.is_symlink():
            shutil.rmtree(dst)
        else:
            dst.unlink()
    try:
        os.symlink(src.resolve(), dst, target_is_directory=True)
    except OSError:
        shutil.copytree(src, dst)


def write_contest_stubs(dest: Path) -> None:
    docs = dest / "docs"
    docs_en = dest / "docs-en"
    docs.mkdir(parents=True, exist_ok=True)
    docs_en.mkdir(parents=True, exist_ok=True)
    zh_src = REPO / "solution" / "CONTEST_README.md"
    en_src = REPO / "solution" / "CONTEST_README_EN.md"
    zh_dst = docs / "contest.md"
    en_dst = docs_en / "contest.md"
    if zh_src.is_file():
        shutil.copy2(zh_src, zh_dst)
    elif not zh_dst.is_file():
        zh_dst.write_text(
            "---\ncomments: true\n---\n\n# 力扣竞赛\n\n竞赛列表暂未包含在本次预览中。\n",
            encoding="utf-8",
        )
    if en_src.is_file():
        shutil.copy2(en_src, en_dst)
    elif not en_dst.is_file():
        en_dst.write_text(
            "---\ncomments: true\n---\n\n# LeetCode Contest\n\nThe contest list is not included in this preview.\n",
            encoding="utf-8",
        )


def tune_mkdocs_yml(path: Path) -> None:
    text = path.read_text(encoding="utf-8")
    text = text.replace(
        "  - minify:\n      minify_html: true\n      minify_js: true\n      minify_css: true\n",
        "",
    )
    path.write_text(text, encoding="utf-8")


def flatten(dest: Path, problem_dirs: List[Path]) -> None:
    listing = dest / "only-dirs.txt"
    rels = [str(p.relative_to(dest)).replace("\\", "/") for p in problem_dirs]
    # problem dirs live under dest/<series>/...
    listing.write_text("\n".join(rels) + "\n", encoding="utf-8")
    env = os.environ.copy()
    env["PREVIEW_ONLY_DIRS_FILE"] = str(listing)
    env["MKDOCS_PREVIEW"] = "1"
    cmd = [sys.executable, str(dest / "build_site.py")]
    p = subprocess.run(cmd, cwd=str(dest), env=env)
    if p.returncode != 0:
        sys.exit(p.returncode)


def ensure_mkdocs(dest: Path) -> None:
    probe = subprocess.run(
        [sys.executable, "-m", "mkdocs", "--version"],
        capture_output=True,
        cwd=str(dest),
    )
    if probe.returncode == 0:
        return
    req = dest / "requirements.txt"
    if not req.is_file():
        sys.exit("mkdocs is not installed and .preview/requirements.txt is missing.")
    print("Installing site dependencies (docs requirements.txt)...")
    inst = subprocess.run(
        [sys.executable, "-m", "pip", "install", "-r", str(req)],
        cwd=str(dest),
    )
    if inst.returncode != 0:
        sys.exit(inst.returncode)


def serve(dest: Path, lang: str, host: str, port: int) -> None:
    config = "mkdocs-en.yml" if lang == "en" else "mkdocs.yml"
    env = os.environ.copy()
    env["MKDOCS_PREVIEW"] = "1"
    env["NO_MKDOCS_2_WARNING"] = "1"
    cmd = [
        sys.executable,
        "-m",
        "mkdocs",
        "serve",
        "-f",
        config,
        "-a",
        f"{host}:{port}",
    ]
    print(f"Serving {lang} at http://{host}:{port}/  (Ctrl+C to stop)")
    p = subprocess.run(cmd, cwd=str(dest), env=env)
    sys.exit(p.returncode)


def main() -> None:
    parser = argparse.ArgumentParser(
        description="Preview selected problems with MkDocs."
    )
    parser.add_argument(
        "--problem", help="Comma-separated ids: 1,2,42 or lcof/3, lcci/01.01"
    )
    parser.add_argument("--range", dest="range", help="LeetCode id range, e.g. 1-99")
    parser.add_argument(
        "--changed", action="store_true", help="Include git-changed problem dirs"
    )
    parser.add_argument("--lang", choices=("zh", "en"), default="zh")
    parser.add_argument("--limit", type=int, default=DEFAULT_LIMIT)
    parser.add_argument("--host", default="127.0.0.1")
    parser.add_argument("--port", type=int, default=8000)
    parser.add_argument(
        "--docs-ref", help="Git ref for the site engine (default: docs)"
    )
    parser.add_argument(
        "--no-serve", action="store_true", help="Prepare .preview/ only"
    )
    args = parser.parse_args()

    selected = select_dirs(args)
    print("Problems:")
    for d in selected:
        print(f"  {d.relative_to(REPO)}")

    if PREVIEW.exists():
        wipe_dir(PREVIEW)
    extract_docs(PREVIEW, args.docs_ref)

    preview_dirs: List[Path] = []
    for src in selected:
        rel = src.relative_to(REPO)
        dst = PREVIEW / rel
        link_or_copy(src, dst)
        preview_dirs.append(dst)

    write_contest_stubs(PREVIEW)
    tune_mkdocs_yml(PREVIEW / "mkdocs.yml")
    en_yml = PREVIEW / "mkdocs-en.yml"
    if en_yml.is_file():
        tune_mkdocs_yml(en_yml)

    flatten(PREVIEW, preview_dirs)
    if args.no_serve:
        print(f"Prepared {PREVIEW}")
        return
    ensure_mkdocs(PREVIEW)
    serve(PREVIEW, args.lang, args.host, args.port)


if __name__ == "__main__":
    main()
