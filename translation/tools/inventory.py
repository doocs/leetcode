#!/usr/bin/env python3
"""Build the source map of the Vietnamese mirror and derive each unit's state.

    python3 translation/tools/inventory.py              # write source map + progress
    python3 translation/tools/inventory.py --check      # CI gate, writes nothing

A unit is one English README (solution/<range>/<dir>/README_EN.md or
lcci/<dir>/README_EN.md) at a pinned commit; its target is vi/<dir>/README.md.
Sources are pinned by git blob SHA-1 read from the commit tree.

State per unit:
  pending     no target file
  unreviewed  target exists without a complete verified unit report
  verified    report is verified for the current source blob and target blob
  stale       report was verified for an older source blob (source changed)
  modified    target changed after it was verified
  <state>     any other state recorded in the unit report (translating, ...)

--check fails on every state except pending, verified and stale (PROJECT-005);
stale is reported as a warning because upstream syncs cause it.

Unit reports live in translation/state/units/<unit id>.yaml.
"""

from __future__ import annotations

import argparse
import subprocess
import sys
from collections import Counter
from dataclasses import dataclass
from pathlib import Path
from typing import Dict, List, Optional

import yaml

REPO = Path(__file__).resolve().parents[2]
STATE = REPO / "translation" / "state"
UNITS = STATE / "units"
SOURCE_MAP = STATE / "source-map.yaml"
PROGRESS = STATE / "PROGRESS.md"
SERIES = (("solution", "lc", 4), ("lcci", "lcci", 3))


@dataclass
class Unit:
    id: str
    order: int
    source: str
    source_blob: str
    target: str
    target_blob: Optional[str] = None
    state: str = "pending"
    report: Optional[str] = None


def git(*args: str) -> str:
    return subprocess.run(
        ["git", *args], cwd=str(REPO), check=True, capture_output=True, text=True
    ).stdout


def make_id(prefix: str, dir_name: str) -> str:
    """lc-0001 for '0001.Two Sum', lcci-01.01 for '01.01.Is Unique'."""
    parts = dir_name.split(".")
    if prefix == "lc":
        return f"lc-{parts[0]}"
    return f"lcci-{parts[0]}.{parts[1]}"


def list_sources(rev: str) -> List[Unit]:
    out = git(
        "-c", "core.quotepath=false", "ls-tree", "-r", rev, "--", "solution", "lcci"
    )
    units: List[Unit] = []
    for line in out.splitlines():
        meta, path = line.split("\t", 1)
        _mode, kind, blob = meta.split()
        parts = path.split("/")
        if kind != "blob" or parts[-1] != "README_EN.md":
            continue
        for series, prefix, depth in SERIES:
            if parts[0] == series and len(parts) == depth:
                problem_dir = "/".join(parts[:-1])
                units.append(
                    Unit(
                        id=make_id(prefix, parts[-2]),
                        order=0,
                        source=path,
                        source_blob=blob,
                        target=f"vi/{problem_dir}/README.md",
                    )
                )
    units.sort(key=lambda u: (u.id.startswith("lcci"), u.id))
    seen = Counter(u.id for u in units)
    dupes = [k for k, v in seen.items() if v > 1]
    if dupes:
        sys.exit(f"duplicate unit ids: {dupes[:10]}")
    for i, unit in enumerate(units, 1):
        unit.order = i
    return units


def worktree_blob(path: Path) -> Optional[str]:
    if not path.is_file():
        return None
    return git("hash-object", "--", str(path)).strip()


def load_report(unit_id: str) -> Optional[dict]:
    path = UNITS / f"{unit_id}.yaml"
    if not path.is_file():
        return None
    return yaml.safe_load(path.read_text(encoding="utf-8")) or {}


def derive_state(unit: Unit, report: Optional[dict]) -> str:
    if unit.target_blob is None:
        return "pending"
    if not report:
        return "unreviewed"
    state = report.get("state") or "unreviewed"
    if state != "verified":
        return state
    source = (report.get("source") or {}).get("git_blob")
    target = (report.get("target") or {}).get("git_blob")
    if not source or not target:
        return "unreviewed"
    if target != unit.target_blob:
        return "modified"
    if source != unit.source_blob:
        return "stale"
    return "verified"


def blocking(units: List[Unit]) -> List[Unit]:
    """Units that must not be on main (PROJECT-005)."""
    return [u for u in units if u.state not in ("pending", "verified", "stale")]


def build(rev: str) -> Dict[str, object]:
    commit = git("rev-parse", rev).strip()
    units = list_sources(commit)
    for unit in units:
        unit.target_blob = worktree_blob(REPO / unit.target)
        report = load_report(unit.id)
        unit.report = f"translation/state/units/{unit.id}.yaml" if report else None
        unit.state = derive_state(unit, report)
    orphans = sorted(
        str(p.relative_to(REPO))
        for p in list((REPO / "vi").glob("solution/*/*/README.md"))
        + list((REPO / "vi").glob("lcci/*/README.md"))
        if str(p.relative_to(REPO)) not in {u.target for u in units}
    )
    return {"commit": commit, "units": units, "orphans": orphans}


def dump_source_map(data: Dict[str, object]) -> str:
    lines = [
        "# Generated by translation/tools/inventory.py; do not edit by hand.",
        "schema_version: 1",
        "inventory_status: ready",
        "source_snapshot:",
        "  kind: git-commit",
        f"  value: {data['commit']}",
        "  unit_hash: git-blob-sha1",
        "scope:",
        "  include: [solution/*/*/README_EN.md, lcci/*/README_EN.md]",
        "units:",
    ]
    for u in data["units"]:
        record = {
            "id": u.id,
            "order": u.order,
            "source": u.source,
            "source_blob": u.source_blob,
            "target": u.target,
            "state": u.state,
        }
        if u.target_blob:
            record["target_blob"] = u.target_blob
        if u.report:
            record["report"] = u.report
        flow = yaml.safe_dump(
            record,
            default_flow_style=True,
            allow_unicode=True,
            sort_keys=False,
            width=10**6,
        ).strip()
        lines.append(f"  - {flow}")
    return "\n".join(lines) + "\n"


def progress_md(data: Dict[str, object]) -> str:
    units: List[Unit] = data["units"]
    counts = Counter(u.state for u in units)
    lines = [
        "# Tiến độ bản dịch tiếng Việt",
        "",
        "Sinh bởi `translation/tools/inventory.py`; không sửa tay.",
        "",
        f"- Snapshot nguồn: `{data['commit']}`",
        f"- Tổng số unit trong scope: {len(units)}",
    ]
    for state in ("verified", "stale", "modified", "unreviewed", "pending"):
        lines.append(f"- {state}: {counts.get(state, 0)}")
    for state in sorted(
        set(counts) - {"verified", "stale", "modified", "unreviewed", "pending"}
    ):
        lines.append(f"- {state}: {counts[state]}")
    started = [u for u in units if u.state != "pending"]
    if started:
        lines += ["", "## Đã bắt đầu", ""]
        lines += [f"- {u.id} — {u.state} — `{u.target}`" for u in started]
    if data["orphans"]:
        lines += ["", "Bản dịch không còn nguồn tương ứng (cần review):", ""]
        lines += [f"- `{p}`" for p in data["orphans"]]
    return "\n".join(lines) + "\n"


def main() -> int:
    parser = argparse.ArgumentParser(
        description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter
    )
    parser.add_argument("--rev", default="HEAD", help="commit to pin sources to")
    parser.add_argument(
        "--check",
        action="store_true",
        help="fail on unreviewed/modified/orphan translations; write nothing",
    )
    args = parser.parse_args()
    data = build(args.rev)
    units: List[Unit] = data["units"]
    counts = Counter(u.state for u in units)
    summary = ", ".join(f"{k}={v}" for k, v in sorted(counts.items()))
    print(f"{len(units)} units at {data['commit'][:12]}: {summary}")
    if args.check:
        bad = blocking(units)
        for u in bad:
            print(f"  {u.state}: {u.target}")
        for p in data["orphans"]:
            print(f"  orphan: {p}")
        for u in units:
            if u.state == "stale":
                print(f"  warning: stale (source changed): {u.target}")
        return 1 if bad or data["orphans"] else 0
    STATE.mkdir(parents=True, exist_ok=True)
    SOURCE_MAP.write_text(dump_source_map(data), encoding="utf-8")
    PROGRESS.write_text(progress_md(data), encoding="utf-8")
    print(f"wrote {SOURCE_MAP.relative_to(REPO)} and {PROGRESS.relative_to(REPO)}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
