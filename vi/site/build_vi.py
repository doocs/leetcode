"""Generate docs-vi/ for the Vietnamese site and return its MkDocs nav.

Every problem that has an English README (solution/ and lcci/) gets a page at
the same relative path as the zh/en sites (lc/{num}.md, lcci/{num}.md):

- translated: vi/<problem dir>/README.md has a verified unit report whose
  target hash matches the file (translation/state/units/*.yaml).
- outdated: same, but README_EN.md changed after the review; published with a
  notice pointing to the English page.
- stub: anything else. A small page (template vi_stub.html, outside the nav)
  links to the English and Chinese pages on the upstream site.

Parsing helpers (headings, numbering, ranges) come from the upstream
build_site.py of the pinned site engine so all three sites share page paths.
"""

from __future__ import annotations

import hashlib
import importlib.util
import json
import re
import shutil
from collections import defaultdict
from pathlib import Path
from typing import Dict, List, Optional, Set, Tuple
from urllib.parse import quote

import yaml

DOCS_VI = "docs-vi"
STATUS_FILE = ".vi_status.json"
TRANSLATED = "translated"
OUTDATED = "outdated"
STUB = "stub"
PUBLISHED = (TRANSLATED, OUTDATED)
STUB_TEMPLATE = "vi_stub.html"

# series root -> (target dir, path depth of README_EN.md)
SERIES = (("solution", "lc", 4), ("lcci", "lcci", 3))

# Static assets shared with the English site.
EN_ASSETS = ("favicon.ico", "logo.png", "assets", "stylesheets", "javascripts")
ZH_ASSETS = ("javascripts/mathjax.js",)

PROGRESS_MARK = "<!-- vi:progress -->"
REPO_MARK = "%REPO%"
UPSTREAM_MARK = "%UPSTREAM%"
# The original 中文 / English site; only Tiếng Việt is built here.
DEFAULT_UPSTREAM = "https://leetcode.doocs.org"

_FRONTMATTER = re.compile(r"\A---\n(.*?)\n---\n", re.S)
_H1 = re.compile(r"^# .+$", re.M)


class BuildError(RuntimeError):
    pass


def load_engine(workdir: Path):
    """Import build_site.py from the engine copied into the work directory."""
    path = workdir / "build_site.py"
    spec = importlib.util.spec_from_file_location("upstream_build_site", path)
    if spec is None or spec.loader is None:
        raise BuildError(f"cannot load {path}")
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


def edit_url(repo: str, repo_path: str) -> str:
    return f"https://github.com/{repo}/edit/main/{quote(repo_path, safe='/')}"


def sha256(path: Path) -> str:
    return hashlib.sha256(path.read_bytes()).hexdigest()


def frontmatter(text: str) -> dict:
    match = _FRONTMATTER.match(text)
    if not match:
        return {}
    data = yaml.safe_load(match.group(1))
    return data if isinstance(data, dict) else {}


def with_meta(text: str, extra: Dict[str, object]) -> str:
    """Append keys to the page's front matter, creating one when missing."""
    lines = "".join(
        f"{key}: {json.dumps(value, ensure_ascii=False)}\n"
        for key, value in extra.items()
    )
    match = _FRONTMATTER.match(text)
    if not match:
        return f"---\n{lines}---\n\n{text}"
    end = match.end(1) + 1
    return text[:end] + lines + text[end:]


def load_reports(reports_dir: Optional[Path]) -> Dict[str, dict]:
    """Unit reports keyed by their target path (vi/.../README.md)."""
    reports: Dict[str, dict] = {}
    if reports_dir is None or not reports_dir.is_dir():
        return reports
    for path in sorted(reports_dir.glob("*.yaml")):
        data = yaml.safe_load(path.read_text(encoding="utf-8")) or {}
        target = (data.get("target") or {}).get("path")
        if target:
            reports[target] = data
    return reports


def publish_state(
    report: Optional[dict], vi_path: Path, en_path: Path
) -> Tuple[str, str]:
    """Return (status, reason) for a translation file (PROJECT-005)."""
    if not report:
        return STUB, "no unit report"
    if report.get("state") != "verified":
        return STUB, f"report state is {report.get('state')!r}"
    target_hash = (report.get("target") or {}).get("sha256")
    if not target_hash or target_hash != sha256(vi_path):
        return STUB, "translation changed after review"
    source_hash = (report.get("source") or {}).get("sha256")
    if source_hash != sha256(en_path):
        return OUTDATED, "English source changed after review"
    return TRANSLATED, ""


def stub_page(en_text: str, target: str, num: str, repo: str, upstream: str) -> str:
    match = _H1.search(en_text)
    if not match:
        raise BuildError(f"no H1 heading in English page for {target}/{num}")
    meta = frontmatter(en_text)
    head = ["---", f"template: {STUB_TEMPLATE}"]
    if meta.get("difficulty"):
        head.append(f"difficulty: {json.dumps(str(meta['difficulty']))}")
    # glightbox injects a script that needs Material's bundle (not in the stub template)
    head += [
        f"vi_status: {STUB}",
        "glightbox: false",
        "search:",
        "  exclude: true",
        "---",
    ]
    body = [
        "",
        match.group(0),
        "",
        '!!! info "Chưa có bản dịch tiếng Việt"',
        "",
        "    Bài này chưa được dịch sang tiếng Việt. Bạn có thể đọc bản gốc:",
        "",
        f"    - [English]({upstream}/en/{target}/{num}/)",
        f"    - [中文]({upstream}/{target}/{num}/)",
        "",
        "    Muốn đóng góp bản dịch? Xem "
        f"[hướng dẫn](https://github.com/{repo}/blob/main/vi/README.md).",
        "",
    ]
    return "\n".join(head + body)


def iter_en_readmes(workdir: Path, series: str, depth: int):
    base = workdir / series
    if not base.is_dir():
        return
    pattern = "/".join(["*"] * (depth - 2) + ["README_EN.md"])
    for path in sorted(base.glob(pattern)):
        yield path, path.parent.relative_to(workdir).as_posix()


def copy_static(
    workdir: Path, docs: Path, static_dir: Path, repo: str, upstream: str
) -> None:
    for item in EN_ASSETS:
        src = workdir / "docs-en" / item
        if src.is_dir():
            shutil.copytree(src, docs / item, dirs_exist_ok=True)
        elif src.is_file():
            shutil.copy2(src, docs / item)
    for item in ZH_ASSETS:
        src = workdir / "docs" / item
        if src.is_file():
            (docs / item).parent.mkdir(parents=True, exist_ok=True)
            shutil.copy2(src, docs / item)
    for src in sorted(static_dir.rglob("*")):
        if not src.is_file():
            continue
        dst = docs / src.relative_to(static_dir)
        dst.parent.mkdir(parents=True, exist_ok=True)
        if src.suffix == ".md":
            text = src.read_text(encoding="utf-8").replace(REPO_MARK, repo)
            text = text.replace(UPSTREAM_MARK, upstream)
            dst.write_text(text, encoding="utf-8")
        else:
            shutil.copy2(src, dst)


def index_page(title: str, group, status) -> str:
    done = sum(1 for item in group if status[item.dest] in PUBLISHED)
    lines = [
        "---",
        "hide:",
        "  - toc",
        "  - feedback",
        "---",
        "",
        f"# {title}",
        "",
        f"Số bài: {len(group)} · đã dịch: {done}",
        "",
    ]
    for item in group:
        mark = "✅ " if status[item.dest] in PUBLISHED else ""
        page = item.dest.rsplit("/", 1)[-1]
        lines.append(f"- {mark}[{item.num}. {item.name}]({page})")
    return "\n".join(lines) + "\n"


def write_indexes(engine, items, docs: Path, status) -> List[Tuple[str, str, list]]:
    """Write one index per 100 LeetCode problems and one for CCI.

    Returns (nav label, index page, group) in nav order.
    """
    sections = []
    buckets = defaultdict(list)
    for item in items["lc"]:
        buckets[engine.range_start(item.num)].append(item)
    for start in sorted(buckets):
        group = sorted(buckets[start], key=lambda x: x.sort_key)
        dest = f"lc/{engine.range_slug(start)}.md"
        label = engine.range_label(start)
        sections.append((label, dest, group))
    if items["lcci"]:
        sections.append(
            ("Cracking the Coding Interview", "lcci/index.md", items["lcci"])
        )
    for label, dest, group in sections:
        out = docs / dest
        out.parent.mkdir(parents=True, exist_ok=True)
        out.write_text(index_page(label, group, status), encoding="utf-8")
    return sections


def nav_yaml(sections, status) -> str:
    """Nav with every index page but only the published problems."""

    def entries(group, indent):
        pad = " " * indent
        return [
            f"{pad}- {item.num}. {item.name}: {item.dest}"
            for item in group
            if status[item.dest] in PUBLISHED
        ]

    lines = ["nav:", "  - Trang chủ: index.md"]
    lc = [s for s in sections if s[1].startswith("lc/")]
    if lc:
        lines.append("  - LeetCode:")
        for label, dest, group in lc:
            lines += [f"    - {label}:", f"      - Mục lục: {dest}"]
            lines += entries(group, 6)
    for label, dest, group in sections:
        if dest.startswith("lcci/"):
            lines += [f"  - {label}:", f"    - Mục lục: {dest}"]
            lines += entries(group, 4)
    lines += ["  - Luyện tập theo chủ đề: tags.md", "  - Contest: contest.md"]
    return "\n".join(lines) + "\n"


def write_progress(docs: Path, translated: int, total: int) -> None:
    index = docs / "index.md"
    if not index.is_file():
        return
    pct = f"{translated * 100 / total:.1f}" if total else "0"
    line = f"Hiện đã dịch **{translated}** / {total} bài ({pct}%)."
    text = index.read_text(encoding="utf-8").replace(PROGRESS_MARK, line)
    index.write_text(text, encoding="utf-8")


def build(
    workdir: Path,
    vi_root: Path,
    static_dir: Path,
    repo: str,
    only: Optional[Set[str]] = None,
    reports_dir: Optional[Path] = None,
    upstream: str = DEFAULT_UPSTREAM,
) -> str:
    """Write docs-vi/ under workdir and return the nav section for mkdocs."""
    engine = load_engine(workdir)
    docs = workdir / DOCS_VI
    docs.mkdir(parents=True, exist_ok=True)
    copy_static(workdir, docs, static_dir, repo, upstream)
    reports = load_reports(reports_dir)

    items: Dict[str, List] = {target: [] for _, target, _ in SERIES}
    status: Dict[str, str] = {"contest.md": STUB}
    for series, target, depth in SERIES:
        for en_path, problem_dir in iter_en_readmes(workdir, series, depth):
            if only is not None and problem_dir not in only:
                continue
            en_text = en_path.read_text(encoding="utf-8")
            num, name = engine.parse_heading(en_text, series)
            dest = f"{target}/{num}.md"
            vi_rel = f"vi/{problem_dir}/README.md"
            vi_path = vi_root / problem_dir / "README.md"
            state = STUB
            if vi_path.is_file():
                state, reason = publish_state(reports.get(vi_rel), vi_path, en_path)
                if state == STUB:
                    print(f"warning: {vi_rel} not published: {reason}")
                elif state == OUTDATED:
                    print(f"warning: {vi_rel}: {reason}")
            if state in PUBLISHED:
                vi_text = vi_path.read_text(encoding="utf-8")
                vi_num, name = engine.parse_heading(vi_text, series)
                if vi_num != num:
                    raise BuildError(
                        f"{vi_path}: heading number {vi_num!r} does not match "
                        f"the English source ({num!r})"
                    )
                page = with_meta(
                    engine.strip_frontmatter_edit_url(vi_text),
                    {"edit_url": edit_url(repo, vi_rel), "vi_status": state},
                )
            else:
                page = stub_page(en_text, target, num, repo, upstream)
            status[dest] = state
            out = docs / dest
            out.parent.mkdir(parents=True, exist_ok=True)
            out.write_text(page, encoding="utf-8")
            items[target].append(
                engine.NavItem(engine.parse_sort_key(num), num, name, dest)
            )
        items[target].sort(key=lambda x: x.sort_key)

    sections = write_indexes(engine, items, docs, status)
    problems = [v for k, v in status.items() if k != "contest.md"]
    write_progress(docs, sum(v in PUBLISHED for v in problems), len(problems))
    (docs / STATUS_FILE).write_text(
        json.dumps(status, ensure_ascii=False, indent=2, sort_keys=True) + "\n",
        encoding="utf-8",
    )
    return nav_yaml(sections, status)
