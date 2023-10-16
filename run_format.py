from typing import List

import os.path
import re
import black

suffixes = ["md", "py", "java", "c", "cpp", "go", "php", "cs", "rs", "js", "ts", "sql"]

code_blocks = ["python", "java", "cpp", "c", "go", "ts", "js", "php", "cs", "rs", "sql"]

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


def add_header(path: str):
    """Add header to php and go files"""
    print(f"[add header] path: {path}")
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    if path.endswith(".php"):
        content = "<?php\n" + content
    elif path.endswith(".go") and "sorting" not in path:
        content = "package main\n" + content
    elif path.endswith(".sql"):
        for func in functions_to_replace:
            pattern = r"\b{}\s*\(".format(func)
            content = re.sub(pattern, f"{func.upper()}(", content, flags=re.IGNORECASE)
    else:
        return
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)


def remove_header(path: str):
    """Remove header from php and go files"""
    print(f"[remove header] path: {path}")
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    if path.endswith(".php"):
        content = content.rstrip()
        content = content.replace("<?php\n", "")
    elif path.endswith(".go"):
        content = content.rstrip()
        if "sorting" not in path:
            content = content.replace("package main\n\n", "").replace(
                "package main\n", ""
            )
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
            if "node_modules" in path or "__pycache__" in path or ".git" in path:
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
                file = f"{root}/Solution2.{suf}"
                with open(file, "w", encoding="utf-8") as f:
                    f.write(block)
                if suf == "go":
                    add_header(file)
                    os.system(f'gofmt -w "{file}"')
                    remove_header(file)
                else:
                    os.system(f'npx clang-format -i --style=file "{file}"')
                with open(file, "r", encoding="utf-8") as f:
                    new_block = f.read()
                if not new_block.endswith("\n"):
                    new_block += "\n"
                content = content.replace(block, new_block)
                os.remove(file)
            elif suf == "python":
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

    with open(path, "w", encoding="utf-8") as f:
        f.write(content)


def run():
    """Start formatting"""
    paths = find_all_paths()

    for path in paths:
        add_header(path)
        if any(path.endswith(suf) for suf in ["c", "cpp", "java"]):
            # format with clang-format
            os.system(f'npx clang-format -i --style=file "{path}"')

    # format with prettier
    os.system('npx prettier --write "**/*.{md,js,ts,php,sql}"')

    # format with gofmt
    os.system("gofmt -w .")

    for path in paths:
        remove_header(path)
    for path in paths:
        format_inline_code(path)


if __name__ == "__main__":
    run()
