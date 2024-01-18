import os
import re
from collections import defaultdict

# 获取 leetcode 题目，结构如下：
"""
- solution
    - 0000-0099
        - 0001.Two Sum/README.md
        - 0002.Add Two Numbers/README.md
    - 0100-0199
        - 0100.Same Tree/README.md
        - 0101.Symmetric Tree/README.md
- lcof
    - 面试题03. 数组中重复的数字/README.md
    - 面试题04. 二维数组中的查找/README.md
- lcof2
    - 剑指 Offer II 001. 整数除法/README.md
    - 剑指 Offer II 002. 二进制加法/README.md
- lcci
    - 01.01.Is Unique/README.md
    - 01.02.Check Permutation/README.md
"""

# 生成 leetcode 题目导航
"""
nav:
  - LeetCode:
    - 1. 两数之和: lc/1.md
    - 2. 两数相加: lc/2.md
    - 100. 相同的树: lc/100.md
  - 剑指 Offer:
    - 面试题3. 数组中重复的数字: lcof/3.md
  - 剑指 Offer（专项突破）：
    - 1. 整数除法: lcof2/1.md
  - 程序员面试金典:
    - 面试题 01.01. 判定字符是否唯一: lcci/1.1.md
"""

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

mapping = {lang: name for name, lang in code_dict.values()}

with open("mkdocs.yml", "r", encoding="utf-8") as f:
    config = f.read()

with open("mkdocs-en.yml", "r", encoding="utf-8") as f:
    en_config = f.read()


def get_paths(dirs: str, m: int):
    paths = []
    for root, _, files in os.walk(dirs):
        for file in files:
            file_name = os.path.join(root, file)
            if file.endswith(".md") and len(file_name.split(os.sep)) == m:
                paths.append(file_name)
    return paths


dirs_mapping = {
    "solution": ("lc", 4),
    "lcof": ("lcof", 3),
    "lcof2": ("lcof2", 3),
    "lcci": ("lcci", 3),
    "lcp": ("lcp", 3),
    "lcs": ("lcs", 3),
}

dirs = ["solution", "lcof", "lcof2", "lcci", "lcp", "lcs"]

"""
nav:
  - LeetCode
    - 1. 两数之和: lc/1.md
    - 2. 两数相加: lc/2.md
"""

navdata_cn = defaultdict(list)
navdata_en = defaultdict(list)

for dir in dirs:
    target_dir, m = dirs_mapping[dir]
    for p in sorted(get_paths(dir, m)):
        with open(p, "r", encoding="utf-8") as f:
            content = f.read()

            # [中文文档](/lcci/01.01.Is%20Unique/README.md)
            # 正则匹配 [中文文档](xxx) 并且移除
            content = re.sub(r"\[中文文档]\((.*?)\)", "", content)
            content = re.sub(r"\[English Version]\((.*?)\)", "", content)

            title = content[content.find("[") + 1 : content.find("]")]
            dot = title.find(".") if dir != "lcci" else title.rfind(".")
            num = (
                title[:dot]
                .replace("面试题", "")
                .replace("剑指 Offer II", "")
                .replace("LCP", "")
                .replace("LCS", "")
                .strip(" ")
                .lstrip("0")
            )
            name = (
                title[dot + 1 :]
                .replace("面试题", "")
                .replace("剑指 Offer II", "")
                .replace("LCP", "")
                .replace("LCS", "")
                .strip(" ")
                .lstrip("0")
            )
            if num.endswith("- III"):
                num = num[:-5] + ".3"
            elif num.endswith("- II"):
                num = num[:-4] + ".2"
            elif num.endswith("- I"):
                num = num[:-3] + ".1"
            num = ".".join([x.strip(" ").lstrip("0") for x in num.split(".")])
            is_en = "README_EN" in p
            if is_en:
                navdata_en[dir].append(f"    - {num}. {name}: {target_dir}/{num}.md")
            else:
                navdata_cn[dir].append(f"    - {num}. {name}: {target_dir}/{num}.md")
            # 修改代码块
            while True:
                start = "<!-- tabs:start -->"
                end = "<!-- tabs:end -->"
                i = content.find(start)
                j = content.find(end)
                if i == -1 or j == -1:
                    break
                j = content.find(end)
                codes = content[i + len(start) : j].strip()
                res = re.findall(r"```(.+?)\n(.+?)\n```", codes, re.DOTALL)
                result = []
                if res:
                    for lang, code in res:
                        name = mapping.get(lang)
                        # 需要将 code 缩进 4 个空格
                        code = code.replace("\n", "\n    ")
                        code_snippet = (
                            f'=== "{name}"\n\n    ```{lang}\n    {code}\n    ```\n'
                        )
                        result.append(code_snippet)
                content = content[:i] + "\n".join(result) + content[j + len(end) :]
            docs_dir = ("docs-en" if is_en else "docs") + os.sep + target_dir
            if not os.path.exists(docs_dir):
                os.makedirs(docs_dir)
            new_path = os.path.join(docs_dir, f"{num}.md")
            with open(new_path, "w", encoding="utf-8") as f:
                f.write(content)

    navdata_en[dir].sort(key=lambda x: int(x.split(".")[0].split(" ")[-1]))
    navdata_cn[dir].sort(key=lambda x: int(x.split(".")[0].split(" ")[-1]))

if "nav:" in config:
    config = config[: config.find("nav:")]
if "nav:" in en_config:
    en_config = en_config[: en_config.find("nav:")]

"""
nav:
  - 首页:
    - 首页: index.md
    - 参与贡献: intro/contribution.md
  - LeetCode 全解:

nav:
  - Home:
    - Home: index.md
    - Contribution: intro/contribution.md
"""

config += "\nnav:\n"
en_config += "\nnav:\n"
config += "  - 首页:\n"
config += "    - 首页: index.md\n"
config += "    - 参与贡献: intro/contribution.md\n"
config += "  - LeetCode 全解:\n"

en_config += "  - LeetCode:\n"
en_config += "    - Home: index.md\n"
en_config += "    - Contribution: intro/contribution.md\n"
config += "\n".join(navdata_cn["solution"])
en_config += "\n".join(navdata_en["solution"])
config += "\n"
en_config += "\n"
config += "  - 剑指 Offer:\n"
config += "\n".join(navdata_cn["lcof"])
config += "\n"
config += "  - 剑指 Offer（专项突破）:\n"
config += "\n".join(navdata_cn["lcof2"])
config += "\n"
config += "  - 程序员面试金典:\n"
config += "\n".join(navdata_cn["lcci"])
config += "\n"
en_config += "  - Cracking the Coding Interview:\n"
en_config += "\n".join(navdata_en["lcci"])
en_config += "\n"

with open("mkdocs.yml", "w", encoding="utf-8") as f:
    f.write(config)

with open("mkdocs-en.yml", "w", encoding="utf-8") as f:
    f.write(en_config)
