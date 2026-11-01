from pathlib import Path
from typing import List, Optional, Tuple
import os
import re
import shutil
import subprocess
import sys
import time

ROOT = Path(__file__).resolve().parent
SUFFIXES = ("md", "py", "java", "c", "cpp", "go", "php", "cs", "rs", "js", "ts", "sql")
SKIP_DIRS = {
    "node_modules",
    "__pycache__",
    ".git",
    ".preview",
    "site-artifacts",
    "site",
}

_INLINE_CODE_RE = re.compile(r"(```(python|java|cpp|c|go|rust)\n)(.*?)(```)", re.S)
_PRETTIER_LOCKED_BLOCK = re.compile(
    r"(?:\[error\] Unable to write file [^\r\n]+:\r?\n)?"
    r"\[error\] (?:UNKNOWN|EBUSY|EPERM|EACCES): [^\r\n]*?\bopen '([^\r\n]+)'\r?\n?"
)

_black_mod = None
_clang_format_exe = None


def _import_black():
    global _black_mod
    if _black_mod is None:
        try:
            import black
        except ImportError as exc:
            raise SystemExit(
                "black is not installed. Run: pnpm run setup:python"
            ) from exc
        _black_mod = black
    return _black_mod


def clang_format_bin() -> str:
    global _clang_format_exe
    if _clang_format_exe is None:
        try:
            from clang_format import get_executable
        except ImportError as exc:
            raise SystemExit(
                "clang-format is not installed. Run: pnpm run setup:python"
            ) from exc
        _clang_format_exe = get_executable("clang-format")
    return _clang_format_exe


def find_all_paths(root: Path = ROOT) -> List[str]:
    """Find all tracked-extension files under ``root``."""
    exts = tuple(f".{s}" for s in SUFFIXES)
    paths: List[str] = []
    for dirpath, dirs, files in os.walk(root):
        dirs[:] = [d for d in dirs if d not in SKIP_DIRS]
        for file in files:
            if file.endswith(exts):
                paths.append(os.path.join(dirpath, file))
    return paths


def git_dirty_paths(
    root: Path = ROOT, exts: Tuple[str, ...] = None
) -> Optional[List[str]]:
    """Return modified or untracked files from git status, or None if git fails."""
    exts = exts or tuple(f".{s}" for s in SUFFIXES)
    try:
        proc = subprocess.run(
            ["git", "-c", "core.quotepath=false", "status", "--porcelain", "-uall"],
            cwd=root,
            capture_output=True,
            encoding="utf-8",
            errors="replace",
            check=False,
        )
        if proc.returncode != 0:
            return None
        paths: List[str] = []
        for line in proc.stdout.splitlines():
            if len(line) < 4:
                continue
            rel = line[3:]
            if " -> " in rel:
                rel = rel.split(" -> ", 1)[1]
            rel = rel.strip('"')
            if rel.lower().endswith(exts):
                full = str(root / rel)
                if os.path.isfile(full):
                    paths.append(full)
        return paths
    except OSError:
        return None


def _go_needs_package_header(path: str) -> bool:
    return "sorting" not in path.replace("\\", "/")


def _add_go_package(content: str, path: str = "") -> str:
    if _go_needs_package_header(path) and not content.lstrip().startswith("package "):
        return "package main\n" + content
    return content


def _strip_go_package(content: str, path: str = "") -> str:
    if _go_needs_package_header(path):
        if content.startswith("package main\n\n"):
            content = content[len("package main\n\n") :]
        elif content.startswith("package main\n"):
            content = content[len("package main\n") :]
    if content and not content.endswith("\n"):
        content += "\n"
    return content


def _gofmt_str(content: str, path: str = "") -> str:
    with_pkg = _add_go_package(content, path)
    added = with_pkg != content
    proc = subprocess.run(
        ["gofmt"],
        input=with_pkg,
        capture_output=True,
        encoding="utf-8",
        errors="replace",
        check=False,
    )
    if proc.returncode != 0:
        raise RuntimeError(proc.stderr.strip() or "gofmt failed")
    out = proc.stdout
    if added:
        return _strip_go_package(out, path)
    if out and not out.endswith("\n"):
        out += "\n"
    return out


def format_go_files(paths: List[str] = None) -> None:
    """Format Go files (handling the temporary `package main` header)."""
    all_mode = paths is None
    if all_mode:
        paths = [p for p in find_all_paths() if p.endswith(".go")]
    else:
        paths = [p for p in paths if p.endswith(".go") and os.path.isfile(p)]
    if not paths:
        return
    if len(paths) <= 32:
        for path in paths:
            with open(path, "r", encoding="utf-8") as f:
                original = f.read()
            formatted = _gofmt_str(original, path)
            if formatted != original:
                with open(path, "w", encoding="utf-8", newline="\n") as f:
                    f.write(formatted)
        return

    headered: List[str] = []
    for path in paths:
        with open(path, "r", encoding="utf-8") as f:
            content = f.read()
        with_pkg = _add_go_package(content, path)
        if with_pkg != content:
            with open(path, "w", encoding="utf-8", newline="\n") as f:
                f.write(with_pkg)
            headered.append(path)
    try:
        if all_mode:
            subprocess.check_call(["gofmt", "-w", "."], cwd=ROOT)
        else:
            for i in range(0, len(paths), 64):
                subprocess.check_call(["gofmt", "-w", *paths[i : i + 64]])
    finally:
        for path in headered:
            with open(path, "r", encoding="utf-8") as f:
                content = f.read()
            with open(path, "w", encoding="utf-8", newline="\n") as f:
                f.write(_strip_go_package(content, path))


def check_gofmt(paths: List[str] = None) -> None:
    """Fail if any Go file differs from gofmt after the package-header roundtrip."""
    if not paths:
        paths = [p for p in find_all_paths() if p.endswith(".go")]
    dirty: List[str] = []
    for path in paths:
        rel = os.path.relpath(path)
        with open(path, "r", encoding="utf-8") as f:
            original = f.read()
        try:
            formatted = _gofmt_str(original, path)
        except RuntimeError as exc:
            dirty.append(f"{rel}: {exc}")
            continue
        if formatted != original:
            dirty.append(rel)
    if dirty:
        print("The following Go files are not gofmt-clean:")
        print("\n".join(dirty))
        raise SystemExit(1)


def format_clang_files(paths: List[str]) -> None:
    targets = [
        p for p in paths if p.endswith((".c", ".cpp", ".java")) and os.path.isfile(p)
    ]
    if not targets:
        return
    cmd = clang_format_bin()
    for i in range(0, len(targets), 64):
        subprocess.check_call([cmd, "-i", "--style=file", *targets[i : i + 64]])


def format_rust_files(paths: List[str], strict: bool = True) -> None:
    rustfmt = shutil.which("rustfmt")
    if not rustfmt:
        print("rustfmt not found; skip Rust formatting")
        return
    rs_files = [p for p in paths if p.endswith(".rs") and os.path.isfile(p)]
    if not rs_files:
        return
    failed = []
    for i in range(0, len(rs_files), 64):
        batch = rs_files[i : i + 64]
        try:
            subprocess.check_call([rustfmt, "--edition", "2021", *batch])
        except subprocess.CalledProcessError:
            for path in batch:
                try:
                    subprocess.check_call([rustfmt, "--edition", "2021", path])
                except subprocess.CalledProcessError as exc:
                    print(f"rustfmt failed: {path}: {exc}")
                    failed.append(path)
    if failed:
        if strict:
            print("The following Rust files are not rustfmt-clean:")
            print("\n".join(failed))
            raise SystemExit(1)
        print(f"rustfmt skipped {len(failed)} file(s)")


def _add_php_headers(paths: List[str]) -> List[str]:
    """Temporarily prepend `<?php\\n` to bare PHP solution files before Prettier."""
    headered: List[str] = []
    for path in paths:
        if not path.endswith(".php") or not os.path.isfile(path):
            continue
        with open(path, "r", encoding="utf-8") as f:
            content = f.read()
        if not content.lstrip().startswith("<?php"):
            with open(path, "w", encoding="utf-8", newline="\n") as f:
                f.write("<?php\n" + content)
            headered.append(path)
    return headered


def _remove_php_headers(paths: List[str]) -> None:
    for path in paths:
        with open(path, "r", encoding="utf-8") as f:
            content = f.read()
        content = content.rstrip().replace("<?php\n", "", 1)
        with open(path, "w", encoding="utf-8", newline="\n") as f:
            f.write(content)


def _prettier_batches(targets: List[str], limit: int = 20000) -> List[List[str]]:
    """Split file targets so the command line stays well under OS limits."""
    batches: List[List[str]] = []
    batch: List[str] = []
    size = 0
    for target in targets:
        extra = len(target) + 3
        if batch and size + extra > limit:
            batches.append(batch)
            batch = []
            size = 0
        batch.append(target)
        size += extra
    if batch:
        batches.append(batch)
    return batches


def _rel_prettier_target(path: str, root: Path) -> str:
    try:
        rel = os.path.relpath(path, root)
    except ValueError:
        rel = path
    return rel.replace("\\", "/")


def run_prettier(root: Path = None, paths: List[str] = None) -> None:
    """Format JS, TS, PHP, SQL, and Markdown with the local Prettier.

    When ``paths`` is provided, only those files are formatted. When a Windows
    sharing violation occurs (``UNKNOWN`` / ``EBUSY`` / ``EPERM`` / ``EACCES``),
    only the locked file paths extracted from stderr are retried (up to three
    times) rather than re-scanning the entire repository.
    """
    root = root or ROOT
    node = shutil.which("node")
    cli = root / "node_modules" / "prettier" / "bin" / "prettier.cjs"
    if not node or not cli.is_file():
        raise SystemExit("node and local prettier are required (pnpm install).")

    if paths is not None:
        exts = (".js", ".ts", ".php", ".sql", ".md")
        targets = list(
            dict.fromkeys(
                _rel_prettier_target(p, root)
                for p in paths
                if p.lower().endswith(exts)
                and os.path.isfile(os.path.join(root, _rel_prettier_target(p, root)))
            )
        )
        if not targets:
            return
        print(f"prettier --write ({len(targets)} file(s))", flush=True)
    else:
        pattern = "**/*.{js,ts,php,sql,md}"
        targets = [pattern]
        print(f"prettier --write {pattern}", flush=True)

    base_cmd = [
        node,
        str(cli),
        "--write",
        "--log-level",
        "warn",
        "--no-error-on-unmatched-pattern",
    ]
    retries = 3
    current_targets = targets
    last_err = b""
    last_returncode = 1

    for attempt in range(1, retries + 2):
        locked_paths: List[str] = []
        for batch in _prettier_batches(current_targets):
            proc = subprocess.run(
                [*base_cmd, *batch],
                cwd=root,
                stderr=subprocess.PIPE,
            )
            err = proc.stderr or b""
            if proc.returncode == 0:
                if err:
                    sys.stderr.buffer.write(err)
                    sys.stderr.flush()
                continue

            last_err = err
            last_returncode = proc.returncode
            text = err.decode("utf-8", "replace")
            batch_locked = [
                _rel_prettier_target(p, root)
                for p in _PRETTIER_LOCKED_BLOCK.findall(text)
                if os.path.isfile(p)
            ]
            remaining = _PRETTIER_LOCKED_BLOCK.sub("", text)
            if not batch_locked or "[error]" in remaining:
                sys.stderr.buffer.write(err)
                sys.stderr.flush()
                raise SystemExit(proc.returncode)
            if remaining.strip():
                sys.stderr.write(remaining)
                sys.stderr.flush()
            for p in batch_locked:
                if p not in locked_paths:
                    locked_paths.append(p)

        if not locked_paths:
            return
        if attempt > retries:
            if last_err:
                sys.stderr.buffer.write(last_err)
                sys.stderr.flush()
            raise SystemExit(last_returncode)
        print(
            f"prettier could not write {len(locked_paths)} locked file(s), "
            f"retrying ({attempt}/{retries})",
            flush=True,
        )
        time.sleep(attempt)
        current_targets = locked_paths


def _format_code_snippet(lang: str, code: str) -> str:
    if not code or not code.strip():
        return code
    if lang in ("c", "cpp", "java"):
        proc = subprocess.run(
            [
                clang_format_bin(),
                "--style=file",
                f"--assume-filename=Solution.{lang}",
            ],
            input=code,
            capture_output=True,
            encoding="utf-8",
            errors="replace",
            check=True,
        )
        out = proc.stdout
    elif lang == "go":
        out = _gofmt_str(code)
    elif lang == "python":
        black = _import_black()
        out = black.format_str(code, mode=black.FileMode(string_normalization=False))
    elif lang == "rust":
        rustfmt = shutil.which("rustfmt") or "rustfmt"
        proc = subprocess.run(
            [rustfmt, "--edition", "2021"],
            input=code,
            capture_output=True,
            encoding="utf-8",
            errors="replace",
            check=True,
        )
        out = proc.stdout
    else:
        return code
    return out if out.endswith("\n") else out + "\n"


def format_inline_code(path: str) -> None:
    """Format inline C/C++/Java/Go/Python/Rust code blocks in a Markdown file in memory."""
    if not path.endswith(".md") or not os.path.isfile(path):
        return
    with open(path, "r", encoding="utf-8") as f:
        original = f.read()

    def _replace(match: re.Match) -> str:
        prefix, lang, block, suffix = match.groups()
        try:
            new_block = _format_code_snippet(lang, block)
        except Exception:
            new_block = block
        return f"{prefix}{new_block}{suffix}"

    updated = _INLINE_CODE_RE.sub(_replace, original)
    if updated != original:
        with open(path, "w", encoding="utf-8", newline="\n") as f:
            f.write(updated)


def run(paths: List[str] = None) -> None:
    """Format standalone files and inline Markdown code blocks."""
    all_mode = paths is None
    target_paths = find_all_paths() if all_mode else paths
    if not target_paths:
        return

    php_headered = _add_php_headers(target_paths)
    try:
        format_clang_files(target_paths)
        run_prettier(ROOT, None if all_mode else target_paths)
        format_go_files(target_paths)
        format_rust_files(target_paths)
    finally:
        _remove_php_headers(php_headered)

    for path in target_paths:
        if path.endswith(".md"):
            format_inline_code(path)


if __name__ == "__main__":
    args = sys.argv[1:]
    if args[:1] == ["--gofmt"]:
        format_go_files(args[1:])
    elif args[:1] == ["--check-gofmt"]:
        check_gofmt(args[1:])
    elif args[:1] == ["--clang-format"]:
        format_clang_files(args[1:])
    elif args == ["--changed"]:
        run(git_dirty_paths() or [])
    elif args:
        print(
            "usage: node scripts/run-py.js run_format.py "
            "[--changed] [--gofmt FILE ...] [--check-gofmt] [--clang-format FILE ...]"
        )
        raise SystemExit(2)
    else:
        run()
