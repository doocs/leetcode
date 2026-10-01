"""Shared paths for the vi/site tests.

Hooks and build_vi reuse the pinned upstream engine, so the tests need it:

    python3 vi/site/prepare.py --export-engine .preview/vi-engine

or VI_ENGINE_DIR pointing at a checkout of the docs branch.
"""

import os
import sys
from pathlib import Path

SITE = Path(__file__).resolve().parents[1]
REPO = SITE.parents[1]


def engine_dir() -> Path:
    path = Path(os.environ.get("VI_ENGINE_DIR", REPO / ".preview" / "vi-engine"))
    if not (path / "build_site.py").is_file():
        raise RuntimeError(
            f"site engine not found at {path}; run "
            "python3 vi/site/prepare.py --export-engine .preview/vi-engine"
        )
    return path


def use_hooks() -> None:
    for path in (engine_dir() / "hooks", SITE / "hooks", SITE):
        if str(path) not in sys.path:
            sys.path.insert(0, str(path))
