#!/usr/bin/env python3
"""Validate existing thinking blocks. Missing blocks are allowed."""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

HEADING = re.compile(r"^### (?:方法|Solution\b|Approach\b|Method\b)", re.M)
BLOCK = re.compile(
    r"<!-- thinking:start -->(.*?)<!-- thinking:end -->",
    re.S,
)
LABEL = re.compile(r"^\*\*(?:思考|Thinking)\*\*$")


def thinking_body(raw: str) -> str:
    lines: list[str] = []
    for line in raw.splitlines():
        text = line.strip()
        if text.startswith(">"):
            text = text[1:].strip()
        if text and not LABEL.match(text):
            lines.append(text)
    return "\n".join(lines)


def check_file(path: Path) -> list[str]:
    text = path.read_text(encoding="utf-8")
    headings = HEADING.findall(text)
    blocks = BLOCK.findall(text)
    errors: list[str] = []
    if len(blocks) > len(headings):
        errors.append(
            f"{path}: {len(blocks)} thinking block(s) but only {len(headings)} method heading(s)"
        )
    for i, raw in enumerate(blocks, 1):
        if not thinking_body(raw):
            errors.append(f"{path}: thinking block {i} is empty")
    return errors


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("files", nargs="*", type=Path)
    args = parser.parse_args()
    files = [p for p in args.files if p.suffix == ".md" and p.is_file()]
    if not files:
        print("No markdown files to check.")
        return 0
    errors: list[str] = []
    for path in files:
        errors.extend(check_file(path))
    if errors:
        print("\n".join(errors))
        return 1
    print(f"OK {len(files)} file(s)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
