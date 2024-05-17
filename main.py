import os
from collections import defaultdict


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

navdata_cn = defaultdict(list)
navdata_en = defaultdict(list)

for dir, (target_dir, m) in dirs_mapping.items():
    for p in sorted(get_paths(dir, m)):
        with open(p, "r", encoding="utf-8") as f:
            content = f.read()
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
            docs_dir = ("docs-en" if is_en else "docs") + os.sep + target_dir
            if not os.path.exists(docs_dir):
                os.makedirs(docs_dir)
            new_path = os.path.join(docs_dir, f"{num}.md")
            with open(new_path, "w", encoding="utf-8") as f:
                f.write(content)

    navdata_en[dir].sort(key=lambda x: int(x.split(".")[0].split(" ")[-1]))
    navdata_cn[dir].sort(key=lambda x: int(x.split(".")[0].split(" ")[-1]))


lc, lcci = "\n".join(navdata_cn["solution"]), "\n".join(navdata_cn["lcci"])
lcof, lcof2 = "\n".join(navdata_cn["lcof"]), "\n".join(navdata_cn["lcof2"])

nav_sections = f"""

nav:
  - 首页: index.md
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
  - Home: index.md
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
