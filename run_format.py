from typing import List

import os.path
import shutil
import subprocess
import sys
import re
from pathlib import Path

suffixes = ["md", "py", "java", "c", "cpp", "go", "php", "cs", "rs", "js", "ts", "sql"]

code_blocks = [
    "python",
    "java",
    "cpp",
    "c",
    "go",
    "ts",
    "js",
    "php",
    "cs",
    "rust",
    "sql",
]

functions_to_replace = [
    "ABS",
    "ACOS",
    "ADDDATE",
    "ADDTIME",
    "AES_DECRYPT",
    "AES_ENCRYPT",
    "ASCII",
    "ASIN",
    "ATAN",
    "AVG",
    "BIN",
    "BIT_COUNT",
    "CEIL",
    "CHAR",
    "CHAR_LENGTH",
    "CHARACTER_LENGTH",
    "CONCAT",
    "CONCAT_WS",
    "CONNECTION_ID",
    "CONV",
    "CONVERT",
    "COS",
    "COT",
    "COUNT",
    "CRC32",
    "CURDATE",
    "CURRENT_DATE",
    "CURRENT_TIME",
    "CURRENT_TIMESTAMP",
    "CURTIME",
    "DATABASE",
    "DATE",
    "DATEDIFF",
    "DATE_ADD",
    "DATE_FORMAT",
    "DATE_SUB",
    "DAY",
    "DAYNAME",
    "DAYOFMONTH",
    "DAYOFWEEK",
    "DAYOFYEAR",
    "DECODE",
    "DEFAULT",
    "DEGREES",
    "DES_DECRYPT",
    "DES_ENCRYPT",
    "ELT",
    "ENCODE",
    "ENCRYPT",
    "EXP",
    "EXPORT_SET",
    "EXTRACT",
    "FIELD",
    "FIND_IN_SET",
    "FLOOR",
    "FORMAT",
    "FOUND_ROWS",
    "FROM_DAYS",
    "FROM_UNIXTIME",
    "GET_FORMAT",
    "GET_LOCK",
    "GREATEST",
    "GROUP_CONCAT",
    "HEX",
    "HOUR",
    "IF",
    "IFNULL",
    "IN",
    "INET_ATON",
    "INET_NTOA",
    "INSERT",
    "INSTR",
    "INTERVAL",
    "ISNULL",
    "LAST_INSERT_ID",
    "LCASE",
    "LEAST",
    "LEFT",
    "LENGTH",
    "LN",
    "LOAD_FILE",
    "LOCALTIME",
    "LOCALTIMESTAMP",
    "LOCATE",
    "LOG",
    "LOG10",
    "LOG2",
    "LOWER",
    "LPAD",
    "LTRIM",
    "MAKE_SET",
    "MAKEDATE",
    "MAKETIME",
    "MATCH",
    "MAX",
    "MD5",
    "MICROSECOND",
    "MID",
    "MIN",
    "MINUTE",
    "MOD",
    "MONTH",
    "MONTHNAME",
    "NAME_CONST",
    "NOW",
    "NULLIF",
    "OCT",
    "OCTET_LENGTH",
    "ORD",
    "PASSWORD",
    "PERIOD_ADD",
    "PERIOD_DIFF",
    "PI",
    "POSITION",
    "POW",
    "POWER",
    "PROCEDURE ANALYSE",
    "QUARTER",
    "QUOTE",
    "RADIANS",
    "RAND",
    "RELEASE_LOCK",
    "REPEAT",
    "REPLACE",
    "REVERSE",
    "RIGHT",
    "ROUND",
    "ROW_COUNT",
    "RPAD",
    "RTRIM",
    "SCHEMA",
    "SEC_TO_TIME",
    "SECOND",
    "SESSION_USER",
    "SHA1",
    "SHA",
    "SIGN",
    "SIN",
    "SLEEP",
    "SOUNDEX",
    "SPACE",
    "SQRT",
    "STR_TO_DATE",
    "STRCMP",
    "SUBDATE",
    "SUBSTR",
    "SUBSTRING",
    "SUBSTRING_INDEX",
    "SUBTIME",
    "SUM",
    "SYSDATE",
    "SYSTEM_USER",
    "TAN",
    "TIME",
    "TIMEDIFF",
    "TIMESTAMP",
    "TIMESTAMPADD",
    "TIMESTAMPDIFF",
    "TIME_FORMAT",
    "TIME_TO_SEC",
    "TO_DAYS",
    "TRIM",
    "TRUNCATE",
    "UCASE",
    "UNCOMPRESS",
    "UNCOMPRESSED_LENGTH",
    "UNHEX",
    "UNIX_TIMESTAMP",
    "UPPER",
    "USER",
    "UTC_DATE",
    "UTC_TIME",
    "UTC_TIMESTAMP",
    "UUID",
    "VAR_POP",
    "VAR_SAMP",
    "VARIANCE",
    "VERSION",
    "WEEK",
    "WEEKDAY",
    "WEEKOFYEAR",
    "XOR",
    "YEAR",
    "YEARWEEK",
    "ROW_NUMBER",
    "RANK",
    "DENSE_RANK",
    "NTILE",
    "LAG",
    "LEAD",
    "FIRST_VALUE",
    "LAST_VALUE",
    "CUME_DIST",
    "PERCENT_RANK",
    "PERCENTILE_CONT",
    "PERCENTILE_DISC",
]


def add_header(path: str, quiet: bool = False) -> bool:
    """Add header to php and go files. Returns True if the file was rewritten."""
    if not quiet:
        print(f"[add header] path: {path}")
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    if path.endswith(".php"):
        content = "<?php\n" + content
    elif path.endswith(".go") and "sorting" not in path:
        if content.startswith("package "):
            return False
        content = "package main\n" + content
    elif path.endswith(".sql"):
        for func in functions_to_replace:
            pattern = r"\b{}\s*\(".format(func)
            content = re.sub(pattern, f"{func.upper()}(", content, flags=re.IGNORECASE)
    else:
        return False
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)
    return True


def remove_header(path: str, quiet: bool = False):
    """Remove header from php and go files"""
    if not quiet:
        print(f"[remove header] path: {path}")
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    if path.endswith(".php"):
        content = content.rstrip()
        content = content.replace("<?php\n", "")
    elif path.endswith(".go"):
        if "sorting" not in path:
            if content.startswith("package main\n\n"):
                content = content[len("package main\n\n") :]
            elif content.startswith("package main\n"):
                content = content[len("package main\n") :]
        if content and not content.endswith("\n"):
            content += "\n"
    else:
        return
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)


def find_all_paths() -> List[str]:
    """Find all paths of files with suffixes"""
    paths = []
    for root, _, files in os.walk(os.getcwd()):
        for file in files:
            path = root + "/" + file
            if (
                "node_modules" in path
                or "__pycache__" in path
                or ".git" in path
                or ".preview" in path.replace("\\", "/")
            ):
                continue
            if any(path.endswith(f".{suf}") for suf in suffixes):
                paths.append(path)
    return paths


def format_inline_code(path: str):
    """Format inline code in .md file"""
    if not path.endswith(".md"):
        return
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    root = path[: path.rfind("/")]
    for suf in code_blocks:
        res = re.findall(f"```{suf}\n(.*?)```", content, re.S)
        for block in res or []:
            # skip empty code block
            if not block or not block.strip():
                continue
            if suf in ["c", "cpp", "java", "go"]:
                file = f"{root}/tmp.{suf}"
                with open(file, "w", encoding="utf-8") as f:
                    f.write(block)
                if suf == "go":
                    added = add_header(file)
                    subprocess.check_call(["gofmt", "-w", file])
                    if added:
                        remove_header(file)
                else:
                    subprocess.check_call(
                        [clang_format_bin(), "-i", "--style=file", file]
                    )
                with open(file, "r", encoding="utf-8") as f:
                    new_block = f.read()
                if not new_block.endswith("\n"):
                    new_block += "\n"
                content = content.replace(block, new_block)
                os.remove(file)
            elif suf == "python":
                black = _import_black()
                new_block = black.format_str(
                    block, mode=black.FileMode(string_normalization=False)
                )
                content = content.replace(block, new_block)
            elif suf == "sql":
                for func in functions_to_replace:
                    pattern = r"\b{}\s*\(".format(func)
                    new_block = re.sub(
                        pattern, f"{func.upper()}(", block, flags=re.IGNORECASE
                    )
                    content = content.replace(block, new_block)
                    block = new_block
            elif suf == "rust":
                file = f"{root}/tmp.rs"
                with open(file, "w", encoding="utf-8") as f:
                    f.write(block)
                rustfmt = shutil.which("rustfmt") or "rustfmt"
                subprocess.check_call([rustfmt, "--edition", "2021", file])
                with open(file, "r", encoding="utf-8") as f:
                    new_block = f.read()
                if not new_block.endswith("\n"):
                    new_block += "\n"
                content = content.replace(block, new_block)
                os.remove(file)

    with open(path, "w", encoding="utf-8") as f:
        f.write(content)


def format_rust_files(paths: List[str]) -> None:
    rustfmt = shutil.which("rustfmt")
    if not rustfmt:
        print("rustfmt not found; skip Rust formatting")
        return
    rs_files = [p for p in paths if p.endswith(".rs")]
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
        print("The following Rust files are not rustfmt-clean:")
        print("\n".join(failed))
        raise SystemExit(1)


def run_prettier(root: Path = None) -> None:
    """Run repo Prettier via node.exe so Windows can find the binary."""
    root = root or Path(__file__).resolve().parent
    prettier = root / "node_modules" / "prettier" / "bin" / "prettier.cjs"
    node = shutil.which("node")
    globs = ["**/*.md", "**/*.js", "**/*.ts", "**/*.php", "**/*.sql"]
    extra = ["--write", "--log-level", "warn", "--no-error-on-unmatched-pattern"]
    env = os.environ.copy()
    opts = env.get("NODE_OPTIONS", "")
    if "max-old-space-size" not in opts:
        env["NODE_OPTIONS"] = f"{opts} --max-old-space-size=8192".strip()

    if node and prettier.is_file():
        cmd = [node, str(prettier), *extra, *globs]
        shell = False
    else:
        npx = shutil.which("npx")
        if not npx:
            raise FileNotFoundError(
                "prettier not found. Install Node.js and run `pnpm install`."
            )
        cmd = [npx, "prettier", *extra, *globs]
        shell = os.name == "nt"
    subprocess.check_call(cmd, cwd=root, env=env, shell=shell)


def run():
    """Start formatting"""
    paths = find_all_paths()

    headered = []
    clang_paths = []
    for path in paths:
        if add_header(path):
            headered.append(path)
        if any(path.endswith(suf) for suf in ["c", "cpp", "java"]):
            clang_paths.append(path)
    format_clang_files(clang_paths)

    run_prettier(Path(__file__).resolve().parent)
    subprocess.check_call(["gofmt", "-w", "."])
    format_rust_files(paths)

    for path in headered:
        remove_header(path)
    for path in paths:
        format_inline_code(path)


_black_mod = None
_clang_format_exe = None


def _import_black():
    global _black_mod
    if _black_mod:
        return _black_mod
    try:
        import black
    except ImportError as exc:
        raise SystemExit("black is not installed. Run: pnpm run setup:python") from exc
    _black_mod = black
    return _black_mod


def clang_format_bin() -> str:
    global _clang_format_exe
    if _clang_format_exe:
        return _clang_format_exe
    try:
        from clang_format import get_executable
    except ImportError as exc:
        raise SystemExit(
            "clang-format is not installed. Run: pnpm run setup:python"
        ) from exc
    _clang_format_exe = get_executable("clang-format")
    return _clang_format_exe


def format_clang_files(paths: List[str]) -> None:
    if not paths:
        return
    cmd = clang_format_bin()
    for i in range(0, len(paths), 64):
        subprocess.check_call([cmd, "-i", "--style=file", *paths[i : i + 64]])


def format_go_files(paths: List[str]) -> None:
    """gofmt staged Go files. Solution files omit `package main`; add it first."""
    for path in paths:
        added = add_header(path, quiet=True)
        subprocess.check_call(["gofmt", "-w", path])
        if added:
            remove_header(path, quiet=True)


def _go_needs_package_header(path: str) -> bool:
    return "sorting" not in path.replace("\\", "/")


def _add_go_package(content: str, path: str) -> str:
    if _go_needs_package_header(path) and not content.startswith("package "):
        return "package main\n" + content
    return content


def _strip_go_package(content: str, path: str) -> str:
    if _go_needs_package_header(path):
        if content.startswith("package main\n\n"):
            content = content[len("package main\n\n") :]
        elif content.startswith("package main\n"):
            content = content[len("package main\n") :]
    if content and not content.endswith("\n"):
        content += "\n"
    return content


def check_gofmt(paths: List[str] = None) -> None:
    """Fail if any Go file differs from gofmt after the package-header roundtrip.

    Copies files into a temp dir so the working tree is never rewritten.
    """
    import tempfile

    if not paths:
        paths = [p for p in find_all_paths() if p.endswith(".go")]
    dirty: List[str] = []
    with tempfile.TemporaryDirectory() as tmp:
        for path in paths:
            rel = os.path.relpath(path)
            dest = os.path.join(tmp, rel)
            os.makedirs(os.path.dirname(dest), exist_ok=True)
            with open(path, "r", encoding="utf-8") as f:
                original = f.read()
            with open(dest, "w", encoding="utf-8", newline="\n") as f:
                f.write(_add_go_package(original, path))
            proc = subprocess.run(
                ["gofmt", "-w", dest],
                capture_output=True,
                encoding="utf-8",
                errors="replace",
                check=False,
            )
            if proc.returncode != 0:
                dirty.append(f"{rel}: {proc.stderr.strip() or 'gofmt failed'}")
                continue
            with open(dest, "r", encoding="utf-8") as f:
                formatted = _strip_go_package(f.read(), path)
            if formatted != original:
                dirty.append(rel)
    if dirty:
        print("The following Go files are not gofmt-clean:")
        print("\n".join(dirty))
        raise SystemExit(1)


if __name__ == "__main__":
    args = sys.argv[1:]
    if args[:1] == ["--gofmt"]:
        format_go_files(args[1:])
    elif args[:1] == ["--check-gofmt"]:
        check_gofmt(args[1:])
    elif args[:1] == ["--clang-format"]:
        format_clang_files(args[1:])
    elif args:
        print(
            "usage: node scripts/run-py.js run_format.py "
            "[--gofmt FILE ...] [--check-gofmt] [--clang-format FILE ...]"
        )
        raise SystemExit(2)
    else:
        run()
