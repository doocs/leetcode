import os
import re
from collections import defaultdict


def format_contest_md(content: str) -> str:
    content = content.replace("[English Version](/solution/CONTEST_README_EN.md)", "")
    content = content.replace("[中文文档](/solution/CONTEST_README.md)", "")
    res = re.findall(r"\[(.*?)\]\((.*?)\)", content)
    for _, link in res:
        num = link.split("/")[-2].split(".")[0]
        num = int(num)
        content = content.replace(link, f"./lc/{num}.md")
    content = f"---\ncomments: true\n---\n\n" + content
    return content


def format_contest_md_en(content: str) -> str:
    content = content.replace("[English Version](/solution/CONTEST_README_EN.md)", "")
    content = content.replace("[中文文档](/solution/CONTEST_README.md)", "")
    res = re.findall(r"\[(.*?)\]\((.*?)\)", content)
    for _, link in res:
        num = link.split("/")[-2].split(".")[0]
        num = int(num)
        content = content.replace(link, f"./lc/{num}.md")
    content = f"---\ncomments: true\n---\n\n" + content
    return content


with open("docs/contest.md", "r", encoding="utf-8") as f:
    contest = f.read()
    contest = format_contest_md(contest)
with open("docs/contest.md", "w", encoding="utf-8") as f:
    f.write(contest)

with open("docs-en/contest.md", "r", encoding="utf-8") as f:
    contest_en = f.read()
    contest_en = format_contest_md_en(contest_en)
with open("docs-en/contest.md", "w", encoding="utf-8") as f:
    f.write(contest_en)

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
                res = re.findall(r"```(.*?)\n(.*?)\n```", codes, re.S)
                result = []
                if res:
                    for lang, code in res:
                        name = mapping.get(lang)
                        code = code or ""
                        # 需要将 code 缩进 4 个空格
                        code = code.replace("\n", "\n    ")
                        code_snippet = f'=== "{name}"\n\n    ```{lang} linenums="1"\n    {code}\n    ```\n'
                        result.append(code_snippet)
                content = content[:i] + "\n".join(result) + content[j + len(end) :]
            docs_dir = ("docs-en" if is_en else "docs") + os.sep + target_dir
            if not os.path.exists(docs_dir):
                os.makedirs(docs_dir)
            new_path = os.path.join(docs_dir, f"{num}.md")

            # 获取 tags
            match = re.search(r"<!-- tags:(.*?) -->", content)
            tag_headers = ""
            if match:
                tags = match.group(1).split(",")
                if tags and tags != [""]:
                    tag_headers = "tags:\n"
                    tag_headers += "".join([f"  - {tag}\n" for tag in tags])
                    tag_headers += "\n"

            # 开启评论
            """
            ---
            comments: true
            ---
            """
            content = f"---\ncomments: true\n{tag_headers}---\n\n" + content
            with open(new_path, "w", encoding="utf-8") as f:
                f.write(content)

    navdata_en[dir].sort(key=lambda x: int(x.split(".")[0].split(" ")[-1]))
    navdata_cn[dir].sort(key=lambda x: int(x.split(".")[0].split(" ")[-1]))


lc, lcci = "\n".join(navdata_cn["solution"]), "\n".join(navdata_cn["lcci"])
lcof, lcof2 = "\n".join(navdata_cn["lcof"]), "\n".join(navdata_cn["lcof2"])

nav_sections = f"""

nav:
  - 首页:
    - 首页: index.md
    - 参与贡献: intro/contribution.md
  - LeetCode 全解:\n{lc}
  - 剑指 Offer:\n{lcof}
  - 剑指 Offer（专项突破）:\n{lcof2}
  - 程序员面试金典:\n{lcci}
  - 专项训练: tags.md
  - 竞赛专区: contest.md

"""

lc, lcci = "\n".join(navdata_en["solution"]), "\n".join(navdata_en["lcci"])

en_nav_sections = f"""

nav:
  - Home:
    - Home: index.md
    - Contribution: intro/contribution.md
  - LeetCode:\n{lc}
  - Cracking the Coding Interview:\n{lcci}
  - Focused Training: tags.md
  - Contest: contest.md

"""

# load mkdocs config
with open("mkdocs.yml", "r", encoding="utf-8") as f:
    config = f.read()
with open("mkdocs-en.yml", "r", encoding="utf-8") as f:
    en_config = f.read()

# delete old nav config
if "nav:" in config:
    config = config[: config.find("nav:")]
if "nav:" in en_config:
    en_config = en_config[: en_config.find("nav:")]

# add new nav config
config += nav_sections
en_config += en_nav_sections

# write new config
with open("mkdocs.yml", "w", encoding="utf-8") as f:
    f.write(config)
with open("mkdocs-en.yml", "w", encoding="utf-8") as f:
    f.write(en_config)
