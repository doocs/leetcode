from itertools import pairwise
import os
import re

code_block_dict = {
    "python": ("Python3", "py"),
    "java": ("Java", "java"),
    "cpp": ("C++", "cpp"),
    "c": ("C", "c"),
    "go": ("Go", "go"),
    "ts": ("TypeScript", "ts"),
    "js": ("JavaScript", "js"),
    "php": ("PHP", "php"),
    "cs": ("C#", "cs"),
    "rust": ("Rust", "rs"),
    "sql": ("MySQL", "sql"),
    "nim": ("Nim", "nim"),
    "scala": ("Scala", "scala"),
    "swift": ("Swift", "swift"),
    "rb": ("Ruby", "rb"),
    "kotlin": ("Kotlin", "kt"),
}


# 抽取代码块
def extract_code():
    paths = []
    for root, _, files in os.walk(os.getcwd()):
        for file in files:
            path = root + "/" + file
            if "node_modules" in path or "__pycache__" in path or ".git" in path:
                continue
            if root == "D:\github-repo\leetcode":
                continue
            if path.endswith("README.md"):
                paths.append(path)
    for path in paths:
        with open(path, "r", encoding="utf-8") as f:
            content = f.read()
        mark = "<!-- tabs:start -->"
        i = content.find(mark)
        if i == -1:
            continue
        content = content[i + len(mark) :]
        for suf, (_, suffix) in code_block_dict.items():
            res = re.findall(f"```{suf}\n(.*?)```", content, re.S)
            if not res:
                continue
            cnt = 1
            for block in res:
                if not block or not block.strip():
                    continue
                if suf in ["java", "cpp", "go", "c"]:
                    block = block.rstrip()
                name = f"{path[:path.rfind('/')]}/Solution{'' if cnt == 1 else str(cnt)}.{suffix}"
                with open(name, "w", encoding="utf-8") as f:
                    f.write(block)
                cnt += 1


def parse_content(content, start, end, titles):
    i = content.find(start)
    if i == -1:
        return []
    j = content.find(end)
    if j == -1:
        return []
    content = content[i + len(start) : j]
    blocks = []
    idx = [content.find(title) for title in titles]
    for l, r in pairwise(idx):
        block = content[l:r].strip()
        if not block:
            continue
        line = block.split("\n")[0]
        method_name = line[2:-2]
        block = block.replace(line, f"### {method_name}")
        blocks.append(block)
    return blocks


def extract_solution_paragraph():
    paths = []
    for root, _, files in os.walk(os.getcwd()):
        for file in files:
            path = root + "/" + file
            if "node_modules" in path or "__pycache__" in path or ".git" in path:
                continue
            if root == "D:\github-repo\leetcode":
                continue
            if path.endswith("README.md") or path.endswith("README_EN.md"):
                paths.append(path)
    for path in paths:
        with open(path, "r", encoding="utf-8") as f:
            content = f.read()

        is_cn = path.endswith("README.md")
        if is_cn:
            blocks = parse_content(
                content,
                "## 解法",
                "<!-- tabs:start -->",
                ["**方法一：", "**方法二：", "**方法三：", "**方法四："],
            )
        else:
            print(path)
            blocks = parse_content(
                content,
                "## Solutions",
                "<!-- tabs:start -->",
                ["**Solution 1:", "**Solution 2:", "**Solution 3:", "**Solution 4:"],
            )

        if blocks:
            prefix = path[: path.rfind("/")]
            name = f"{prefix}/Solution.md" if is_cn else f"{prefix}/Solution_EN.md"
            with open(name, "w", encoding="utf-8") as f:
                f.write("\n\n".join(blocks))


if __name__ == "__main__":
    # extract_code()
    extract_solution_paragraph()
