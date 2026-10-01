#!/usr/bin/env python3
"""Mechanical checks for Vietnamese translations against their English source.

    python3 translation/tools/check_vi.py "vi/solution/0000-0099/0001.Two Sum/README.md"
    python3 translation/tools/check_vi.py --all

The source of vi/<problem dir>/README.md is <problem dir>/README_EN.md.

Errors (exit 1): front matter, title line, HTML comment markers, fenced code
(excluding approved explanatory comment text),
heading levels, code-tab headings, inline code, math, URLs, every HTML tag
(opening and closing), the number of blank-line separated blocks and example
data in <pre> blocks must match the source.

Warnings (QA-06, classify by hand): prose lines that still contain several
English function words.

These checks do not replace the full source-vs-target semantic review.
"""

from __future__ import annotations

import argparse
import re
import sys
from collections import Counter
from pathlib import Path
from typing import List, Tuple

REPO = Path(__file__).resolve().parents[2]
VI_ROOT = "vi"
SERIES = ("solution", "lcci")

_FRONTMATTER = re.compile(r"\A---\n.*?\n---\n", re.S)
_FENCE_OPEN = re.compile(r"^(?P<fence>`{3,}|~{3,})(?P<info>.*)$")
_COMMENT = re.compile(r"<!--.*?-->", re.S)
_HEADING = re.compile(r"^(#{1,6}) (.*)$", re.M)
_BACKTICK = re.compile(r"(`+)(.+?)\1", re.S)
_HTML_CODE = re.compile(r"<code>(.*?)</code>", re.S)
_MATH = re.compile(r"\$\$.+?\$\$|\$[^$\n]+?\$", re.S)
_MD_URL = re.compile(r"\]\(([^)\s]+)")
_ATTR_URL = re.compile(r"""\b(?:href|src)=["']([^"']+)["']""")
_PRE = re.compile(r"<pre>(.*?)</pre>", re.S)
_DATA_LINE = re.compile(r"<strong>(?:Input|Output):\s*</strong>(.*)$")
_VI_DATA_LINE = re.compile(r"<strong>[^<]*:\s*</strong>(.*)$")
_TAG = re.compile(r"<[^>]+>")
_TAG_NAME = re.compile(r"<(/?)([A-Za-z][A-Za-z0-9]*)\b")
_HASH_COMMENT_LANGUAGES = {
    "bash",
    "mysql",
    "nim",
    "perl",
    "python",
    "ruby",
    "shell",
    "sh",
}
_SLASH_COMMENT_LANGUAGES = {
    "c",
    "c#",
    "c++",
    "cpp",
    "cs",
    "css",
    "dart",
    "go",
    "java",
    "javascript",
    "js",
    "kotlin",
    "php",
    "rust",
    "scala",
    "swift",
    "ts",
    "typescript",
}
_SQL_COMMENT_LANGUAGES = {
    "mariadb",
    "mysql",
    "oracle",
    "plsql",
    "postgres",
    "postgresql",
    "sqlite",
    "sql",
    "tsql",
}
_PROTECTED_COMMENT = re.compile(
    r"^(?:#|//|--|/\*|<!--)?\s*(?:!|noqa|type:|fmt:|pylint:|pragma:|"
    r"go:|clang-format|format:|eslint|nolint|ts-ignore|istanbul|region|endregion)",
    re.I,
)
ENGLISH_WORDS = {
    "the",
    "and",
    "of",
    "is",
    "are",
    "we",
    "to",
    "in",
    "for",
    "with",
    "that",
    "this",
    "then",
    "each",
    "if",
    "where",
    "which",
    "be",
    "can",
    "you",
    "return",
    "otherwise",
}


def source_for(target: Path) -> Path:
    rel = target.resolve().relative_to(REPO / VI_ROOT)
    return REPO / rel.parent / "README_EN.md"


def split_fences(text: str) -> Tuple[List[Tuple[str, str]], str]:
    """Return fenced blocks (info, body) and the text with fences blanked."""
    blocks, kept, current, body = [], [], None, []
    for line in text.split("\n"):
        if current is None:
            match = _FENCE_OPEN.match(line)
            if match:
                current = (match.group("fence"), match.group("info"))
                body = []
                kept.append("\x00FENCE\x00")
            else:
                kept.append(line)
        elif line.startswith(current[0][0] * len(current[0])) and not line.strip(
            current[0][0]
        ):
            blocks.append((current[1], "\n".join(body)))
            current = None
        else:
            body.append(line)
    if current is not None:
        blocks.append((current[1], "\n".join(body) + "\n<UNCLOSED FENCE>"))
    return blocks, "\n".join(kept)


def without_code_comments(code: str, info: str) -> str:
    """Remove translatable comments while retaining code and tool directives."""
    language = info.strip().split(maxsplit=1)[0].lower() if info.strip() else ""
    line_markers = []
    if language in _HASH_COMMENT_LANGUAGES or language in _SQL_COMMENT_LANGUAGES:
        line_markers.append("#")
    if language in _SLASH_COMMENT_LANGUAGES:
        line_markers.append("//")
    if language in _SQL_COMMENT_LANGUAGES:
        line_markers.append("--")
    block_markers = ("<!--", "-->") if language in {"html", "xml"} else ("/*", "*/")
    if (
        not line_markers
        and language not in _SLASH_COMMENT_LANGUAGES
        and language
        not in {
            "html",
            "xml",
        }
    ):
        return code

    out = []
    quote = None
    i = 0
    while i < len(code):
        if quote:
            if code.startswith(quote, i):
                out.append(quote)
                i += len(quote)
                quote = None
            else:
                if code[i] == "\\" and i + 1 < len(code):
                    out.append(code[i : i + 2])
                    i += 2
                else:
                    out.append(code[i])
                    i += 1
            continue
        if code.startswith(block_markers[0], i):
            end = code.find(block_markers[1], i + len(block_markers[0]))
            end = len(code) if end == -1 else end + len(block_markers[1])
            comment = code[i:end]
            if _PROTECTED_COMMENT.search(comment):
                out.append(comment)
            else:
                out.append("".join("\n" if char == "\n" else "" for char in comment))
            i = end
            continue
        marker = next((item for item in line_markers if code.startswith(item, i)), None)
        if marker:
            end = code.find("\n", i)
            end = len(code) if end == -1 else end
            comment = code[i:end]
            out.append(comment if _PROTECTED_COMMENT.search(comment) else "")
            i = end
            continue
        if code.startswith('"""', i) or code.startswith("'''", i):
            quote = code[i : i + 3]
            out.append(quote)
            i += 3
        elif code[i] in {'"', "'", "`"}:
            quote = code[i]
            out.append(code[i])
            i += 1
        else:
            out.append(code[i])
            i += 1
    return "".join(out)


def tab_headings(text: str) -> List[str]:
    out = []
    for region in re.findall(r"<!-- tabs:start -->(.*?)<!-- tabs:end -->", text, re.S):
        out += [h for h in re.findall(r"^#### (.*)$", region, re.M)]
    return out


def inline_code(text: str) -> Counter:
    spans = [m.group(2) for m in _BACKTICK.finditer(text)]
    spans += _HTML_CODE.findall(text)
    return Counter(spans)


def without_code(text: str) -> str:
    return _HTML_CODE.sub(" ", _BACKTICK.sub(" ", text))


def urls(text: str) -> Counter:
    return Counter(_MD_URL.findall(text) + _ATTR_URL.findall(text))


def tag_counts(text: str) -> Counter:
    """Every opening and closing HTML tag, by name (inline code excluded)."""
    text = _BACKTICK.sub(" ", text)
    return Counter(f"{close}{name.lower()}" for close, name in _TAG_NAME.findall(text))


def block_count(text: str) -> int:
    """Blank-line separated blocks (paragraphs, lists, tables, HTML blocks)."""
    return sum(1 for block in re.split(r"\n\s*\n", text) if block.strip())


def pre_data_errors(src: str, dst: str) -> List[str]:
    errors = []
    src_pres, dst_pres = _PRE.findall(src), _PRE.findall(dst)
    for i, (a, b) in enumerate(zip(src_pres, dst_pres), 1):
        a_lines, b_lines = a.strip("\n").split("\n"), b.strip("\n").split("\n")
        if len(a_lines) != len(b_lines):
            errors.append(f"<pre> #{i}: {len(a_lines)} lines in source, {len(b_lines)}")
            continue
        for j, (x, y) in enumerate(zip(a_lines, b_lines), 1):
            data = _DATA_LINE.search(x)
            if not data:
                continue
            got = _VI_DATA_LINE.search(y)
            if not got or got.group(1).strip() != data.group(1).strip():
                errors.append(
                    f"<pre> #{i} line {j}: example data differs: {y.strip()!r}"
                )
    return errors


def diff_counter(name: str, a: Counter, b: Counter) -> List[str]:
    if a == b:
        return []
    missing = list((a - b).elements())[:5]
    extra = list((b - a).elements())[:5]
    return [f"{name}: missing {missing!r}, extra {extra!r}"]


def residual_english(text: str) -> List[str]:
    hits = []
    prose = _PRE.sub(" ", text)
    for no, line in enumerate(prose.split("\n"), 1):
        if line.startswith("#### ") or "\x00FENCE\x00" in line:
            continue
        plain = _MATH.sub(" ", without_code(_TAG.sub(" ", _COMMENT.sub(" ", line))))
        plain = _MD_URL.sub("](", plain)
        words = {w.lower() for w in re.findall(r"[A-Za-z]+", plain)}
        found = sorted(words & ENGLISH_WORDS)
        if len(found) >= 2:
            hits.append(f"line ~{no}: {found} :: {line.strip()[:90]}")
    return hits


def check_pair(src_text: str, dst_text: str) -> Tuple[List[str], List[str]]:
    errors: List[str] = []
    src_fm = _FRONTMATTER.match(src_text)
    dst_fm = _FRONTMATTER.match(dst_text)
    if (src_fm and src_fm.group(0)) != (dst_fm and dst_fm.group(0)):
        errors.append("frontmatter: differs from the source")

    src_blocks, src_rest = split_fences(src_text)
    dst_blocks, dst_rest = split_fences(dst_text)
    src_code = [(info, without_code_comments(body, info)) for info, body in src_blocks]
    dst_code = [(info, without_code_comments(body, info)) for info, body in dst_blocks]
    if src_code != dst_code:
        errors.append(
            f"code_fences: {len(src_blocks)} in source, {len(dst_blocks)} in target "
            "or content/info string differs"
        )

    src_h = _HEADING.findall(src_rest)
    dst_h = _HEADING.findall(dst_rest)
    if [lvl for lvl, _ in src_h] != [lvl for lvl, _ in dst_h]:
        errors.append("headings: level sequence differs")
    src_h1 = [t for lvl, t in src_h if lvl == "#"]
    dst_h1 = [t for lvl, t in dst_h if lvl == "#"]
    if src_h1 != dst_h1:
        errors.append(f"h1: {dst_h1!r} != {src_h1!r}")
    if tab_headings(src_rest) != tab_headings(dst_rest):
        errors.append("tabs: code-tab headings differ")

    if _COMMENT.findall(src_rest) != _COMMENT.findall(dst_rest):
        errors.append("markers: HTML comment sequence differs")
    errors += diff_counter("inline_code", inline_code(src_rest), inline_code(dst_rest))
    errors += diff_counter(
        "math",
        Counter(_MATH.findall(without_code(src_rest))),
        Counter(_MATH.findall(without_code(dst_rest))),
    )
    errors += diff_counter("urls", urls(src_rest), urls(dst_rest))
    errors += diff_counter("html_tags", tag_counts(src_rest), tag_counts(dst_rest))
    if block_count(src_rest) != block_count(dst_rest):
        errors.append(
            f"blocks: {block_count(src_rest)} in source, {block_count(dst_rest)} "
            "in target (paragraph added, removed or merged)"
        )
    errors += pre_data_errors(src_rest, dst_rest)
    return errors, residual_english(dst_rest)


def all_targets() -> List[Path]:
    root = REPO / VI_ROOT
    found = sorted(root.glob("solution/*/*/README.md"))
    return found + sorted(root.glob("lcci/*/README.md"))


def main() -> int:
    parser = argparse.ArgumentParser(
        description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter
    )
    parser.add_argument("files", nargs="*", type=Path)
    parser.add_argument("--all", action="store_true", help="check every vi README")
    parser.add_argument("--quiet", action="store_true", help="hide warnings")
    args = parser.parse_args()
    targets = all_targets() if args.all else [p.resolve() for p in args.files]
    if not targets:
        print("No translations to check.")
        return 0
    failed = 0
    for target in targets:
        rel = target.relative_to(REPO)
        source = source_for(target)
        if not source.is_file():
            print(f"FAIL {rel}\n  source missing: {source.relative_to(REPO)}")
            failed += 1
            continue
        errors, warnings = check_pair(
            source.read_text(encoding="utf-8"), target.read_text(encoding="utf-8")
        )
        print(f"{'FAIL' if errors else 'PASS'} {rel}")
        for e in errors:
            print(f"  error: {e}")
        if not args.quiet:
            for w in warnings:
                print(f"  warn: residual_english {w}")
        failed += bool(errors)
    print(f"{len(targets) - failed}/{len(targets)} passed")
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
