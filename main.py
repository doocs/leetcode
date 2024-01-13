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
    suffixes = [suf for _, (_, suf) in code_block_dict.items()]
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


if __name__ == "__main__":
    extract_code()
