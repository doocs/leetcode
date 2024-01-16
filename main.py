from itertools import pairwise
import os
import re

sorted_suffixes = [
    "py",
    "java",
    "cpp",
    "go",
    "ts",
    "rs",
    "js",
    "cs",
    "php",
    "c",
    "scala",
    "swift",
    "rb",
    "kt",
    "nim",
    "sql",
]
code_dict = {
    "py": ("Python3", "python"),
    "java": ("Java", "java"),
    "cpp": ("C++", "cpp"),
    "go": ("Go", "go"),
    "ts": ("TypeScript", "ts"),
    "rs": ("Rust", "rust"),
    "js": ("JavaScript", "js"),
    "cs": ("C#", "cs"),
    "php": ("PHP", "php"),
    "c": ("C", "c"),
    "scala": ("Scala", "scala"),
    "swift": ("Swift", "swift"),
    "rb": ("Ruby", "rb"),
    "kt": ("Kotlin", "kotlin"),
    "nim": ("Nim", "nim"),
    "sql": ("MySQL", "sql"),
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
        for suffix, (_, suf) in code_dict.items():
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

        prefix = path[: path.rfind("/")]
        tab_start = "<!-- tabs:start -->"
        tab_end = "<!-- tabs:end -->"
        for i in range(1, 5):
            codes = []
            for suf in sorted_suffixes:
                seq = '' if i == 1 else str(i)
                file_name = f"{prefix}/Solution{seq}.{suf}"
                try:
                    with open(file_name, "r", encoding="utf-8") as f:
                        code = f.read().strip()
                        code = '```' + code_dict[suf][1] + '\n' + code + '\n```'
                        codes.append(code)
                except:
                    continue
            if codes:
                if i > len(blocks):
                    seq_dict = {1: '一', 2: '二', 3: '三', 4: '四'}
                    title = f"### 方法{seq_dict[i]}" if is_cn else f"### Solution {i}"
                    block = (
                        title
                        + '\n\n'
                        + tab_start
                        + '\n\n'
                        + '\n\n'.join(codes)
                        + '\n\n'
                        + tab_end
                    )
                    blocks.append(block)
                else:
                    block = (
                        blocks[i - 1]
                        + '\n\n'
                        + tab_start
                        + '\n\n'
                        + '\n\n'.join(codes)
                        + '\n\n'
                        + tab_end
                    )
                    blocks[i - 1] = block
        is_problem = (
            content.find("## 解法") != -1
            if is_cn
            else content.find("## Solutions") != -1
            and content.find("## Description") != -1
        )
        start = '## 解法' if is_cn else '## Solutions'
        end = '<!-- end -->'
        if blocks:
            content = (
                content[: content.find(start)]
                + start
                + '\n\n'
                + '\n\n'.join(blocks)
                + '\n\n'
                + end
            )
            with open(path, "w", encoding="utf-8") as f:
                f.write(content)
        elif is_problem:
            start = '## 解法' if is_cn else '## Solutions'
            content = content[: content.find(start)] + start + '\n\n' + end
            with open(path, "w", encoding="utf-8") as f:
                f.write(content)


if __name__ == "__main__":
    extract_code()
    extract_solution_paragraph()
