import json
import os
import re
import requests
import yaml
from typing import Tuple, List
from urllib.parse import quote, unquote


def load_template(template_name: str) -> str:
    with open("./template.md", "r", encoding="utf-8") as f:
        content = f.read()
        start_text = f"<!-- 这里是{template_name}开始位置 -->"
        end_text = f"<!-- 这里是{template_name}结束位置 -->"
        content = re.search(f"{start_text}(.*?){end_text}", content, re.S).group(1)
        return content.strip()


def load_ratings():
    res = {}
    if os.path.exists("rating.json"):
        with open("rating.json", "r", encoding="utf-8") as f:
            ratings = json.loads(f.read())
            for item in ratings:
                res[str(item["ID"])] = item

    url = "https://zerotrac.github.io/leetcode_problem_rating/data.json"
    try:
        resp = requests.get(url)
        if resp.status_code == 200:
            ratings = resp.json()
            for item in ratings:
                res[str(item["ID"])] = item
    except Exception as e:
        print(f"Failed to fetch ratings: {e}")
    return res


rating_dict = load_ratings()


readme_cn = load_template("readme_template")
readme_en = load_template("readme_template_en")
problem_readme_cn = load_template("problem_readme_template")
problem_readme_en = load_template("problem_readme_template_en")
sql_readme_cn = load_template("sql_problem_readme_template")
sql_readme_en = load_template("sql_problem_readme_template_en")
bash_readme_cn = load_template("bash_problem_readme_template")
bash_readme_en = load_template("bash_problem_readme_template_en")
ts_readme_cn = load_template("ts_problem_readme_template")
ts_readme_en = load_template("ts_problem_readme_template_en")
contest_readme_cn = load_template("contest_readme_template")
contest_readme_en = load_template("contest_readme_template_en")
category_readme_cn = load_template("category_readme_template")
category_readme_en = load_template("category_readme_template_en")

category_dict = {"Database": "数据库"}


def load_cookies() -> Tuple[str, str]:
    cookie_cn, cookie_en = "", ""
    env_file = "./.env"
    if not os.path.exists(env_file):
        return cookie_cn, cookie_en
    with open(env_file, "r") as f:
        lines = f.readlines()
        for line in lines:
            if line.startswith("COOKIE_CN"):
                parts = line.split("=")[1:]
                cookie_cn = "=".join(parts).strip().strip('"')
                continue

            if line.startswith("COOKIE_EN"):
                parts = line.split("=")[1:]
                cookie_en = "=".join(parts).strip().strip('"')
                continue
    return cookie_cn, cookie_en


def load_refresh_config() -> bool:
    env_file = "./.env"
    if not os.path.exists(env_file):
        return False
    with open(env_file, "r") as f:
        lines = f.readlines()
        for line in lines:
            if line.startswith("REFRESH"):
                parts = line.split("=")[1:]
                return "=".join(parts).strip().strip('"') == "True"
    return False


def load_result() -> List[dict]:
    result_file = "./result.json"
    if not os.path.exists(result_file):
        return []
    with open(result_file, "r", encoding="utf-8") as f:
        res = f.read()
        return json.loads(res)


def load_contest_result() -> List[dict]:
    contest_result_file = "./contest.json"
    if not os.path.exists(contest_result_file):
        return []
    with open(contest_result_file, "r", encoding="utf-8") as f:
        res = f.read()
        if res:
            return json.loads(res)
        return []


def save_result(data: List[dict]):
    with open("./result.json", "w", encoding="utf-8") as f:
        f.write(json.dumps(data))


def save_contest_result(data: List[dict]):
    with open("./contest.json", "w", encoding="utf-8") as f:
        f.write(json.dumps(data))


def select_templates(category):
    if category == "Shell":
        return [bash_readme_cn, bash_readme_en]
    if category == "Database":
        return [sql_readme_cn, sql_readme_en]
    if category == "JavaScript" or category == "TypeScript":
        return [ts_readme_cn, ts_readme_en]
    return [problem_readme_cn, problem_readme_en]


def generate_readme(result):
    md_table_cn = [item["md_table_row_cn"] for item in result]
    md_table_en = [item["md_table_row_en"] for item in result]

    # generate README.md
    items = []
    table_cn = "\n|  题号  |  题解  |  标签  |  难度  | 备注 |\n| --- | --- | --- | --- | --- |"
    for item in sorted(md_table_cn, key=lambda x: x[0]):
        items.append(
            f"\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |"
        )
    table_cn += "".join(items)

    # generate README_EN.md
    items = []
    table_en = "\n|  #  |  Solution  |  Tags  |  Difficulty  |  Remark |\n| --- | --- | --- | --- | --- |"
    for item in sorted(md_table_en, key=lambda x: x[0]):
        items.append(
            f"\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |"
        )
    table_en += "".join(items)

    with open("./README.md", "w", encoding="utf-8") as f:
        f.write(readme_cn.format(table_cn))
    with open("./README_EN.md", "w", encoding="utf-8") as f:
        f.write(readme_en.format(table_en))


def generate_question_readme(result):
    for item in result:
        if not item["content_cn"] and not item["content_en"]:
            continue
        path = (
            f'./{item["sub_folder"]}/{item["frontend_question_id"]}.{item["title_en"]}'
        )
        path = path.replace(":", " ")
        if os.path.isdir(path):
            continue
        os.makedirs(path)

        # choose the readme template
        category = item["category"]
        readme_template_cn, readme_template_en = select_templates(category)
        paid_only = " 🔒" if item["paid_only"] else ""
        rating_item = rating_dict.get(str(item["frontend_question_id"]))
        rating = rating_item.get("Rating", 0) if rating_item else ""
        source = (
            rating_item.get("ContestID_zh") + " " + rating_item.get("ProblemIndex")
            if rating_item
            else ""
        )
        # 生成 metadata
        """
        ---
        tags:
          - 数组
          - 哈希表
        difficulty: 简单
        rating: 1234
        comments: true
        edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0001.Two%20Sum/README.md
        ---
        """
        cat = category_dict.get(category, category)
        cat = cat.title() if cat and cat[0].islower() else cat
        metadata = {
            "tags": item["tags_cn"] or [cat],
            "difficulty": item["difficulty_cn"],
            "rating": rating,
            "comments": True,
            "edit_url": f'https://github.com/doocs/leetcode/edit/main{item["relative_path_cn"]}',
            "source": source,
        }
        if not item["tags_cn"] or metadata["tags"] == ["Algorithms"]:
            metadata.pop("tags")
        if not rating:
            metadata.pop("rating")
        if not source:
            metadata.pop("source")
        yaml_metadata = yaml.dump(
            metadata, default_flow_style=False, allow_unicode=True
        )
        metadata_section = f"---\n{yaml_metadata}---"

        # generate lc-cn problem readme
        with open(f"{path}/README.md", "w", encoding="utf-8") as f1:
            f1.write(
                readme_template_cn.format(
                    metadata_section,
                    int(item["frontend_question_id"]),
                    item["title_cn"].strip() + paid_only,
                    item["url_cn"],
                    item["relative_path_en"],
                    item["content_cn"].replace("leetcode-cn.com", "leetcode.cn"),
                )
            )

        source = (
            rating_item.get("ContestID_en") + " " + rating_item.get("ProblemIndex")
            if rating_item
            else ""
        )

        cat = category.title() if category and category[0].islower() else category
        metadata = {
            "tags": item["tags_en"] or [cat],
            "difficulty": item["difficulty_en"],
            "rating": rating,
            "comments": True,
            "edit_url": f'https://github.com/doocs/leetcode/edit/main{item["relative_path_en"]}',
            "source": source,
        }
        if not item["tags_cn"] or metadata["tags"] == ["Algorithms"]:
            metadata.pop("tags")
        if not rating:
            metadata.pop("rating")
        if not source:
            metadata.pop("source")
        yaml_metadata = yaml.dump(
            metadata, default_flow_style=False, allow_unicode=True
        )
        metadata_section = f"---\n{yaml_metadata}---"

        # generate lc-en problem readme
        with open(f"{path}/README_EN.md", "w", encoding="utf-8") as f2:
            f2.write(
                readme_template_en.format(
                    metadata_section,
                    int(item["frontend_question_id"]),
                    item["title_en"].strip() + paid_only,
                    item["url_en"],
                    item["relative_path_cn"],
                    item["content_en"],
                )
            )


def generate_category_summary(result, category=""):
    """generate category summary files"""
    summary_cn = (
        "- " + category_dict.get(category, category) + "专项练习\n\n"
        if category
        else ""
    )
    summary_en = "- " + category + " Practice\n\n" if category else ""
    category = category.lower() if category else ""
    sub_category = category + "-" if category else ""
    m = {int(item["frontend_question_id"]): item for item in result}
    for file in sorted(os.listdir("./"), key=lambda x: x.lower()):
        if os.path.isdir("./" + file) and file != "__pycache__":
            for sub in sorted(os.listdir("./" + file), key=lambda x: x.lower()):
                sub = sub.replace("`", " ")
                enc = quote(sub)
                if not sub[:4].isdigit():
                    continue
                data = m.get(int(sub[:4]))
                if not data or (category and data["category"].lower() != category):
                    continue
                sub_cn = sub
                if data:
                    sub_cn = sub[:5] + data["title_cn"]

                summary_cn += (
                    f"  - [{sub_cn}](/{sub_category}solution/{file}/{enc}/README.md)\n"
                )
                summary_en += (
                    f"  - [{sub}](/{sub_category}solution/{file}/{enc}/README_EN.md)\n"
                )

    # generate summary.md
    with open(f"./{sub_category}summary.md", "w", encoding="utf-8") as f:
        f.write(summary_cn)

    # generate summary_en.md
    with open(f"./{sub_category}summary_en.md", "w", encoding="utf-8") as f:
        f.write(summary_en)


def generate_category_readme(result, category=""):
    md_table_cn = [item["md_table_row_cn"] for item in result]
    md_table_en = [item["md_table_row_en"] for item in result]
    m = {int(item["frontend_question_id"]): item for item in result}

    # generate README.md
    items = []
    table_cn = "\n|  题号  |  题解  |  标签  |  难度  | 备注 |\n| --- | --- | --- | --- | --- |"
    for item in sorted(md_table_cn, key=lambda x: x[0]):
        if category and m[int(item[0])]["category"] != category:
            continue
        items.append(
            f"\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |"
        )
    table_cn += "".join(items)

    # generate README_EN.md
    items = []
    table_en = "\n|  #  |  Solution  |  Tags  |  Difficulty  |  Remark |\n| --- | --- | --- | --- | --- |"
    for item in sorted(md_table_en, key=lambda x: x[0]):
        if category and m[int(item[0])]["category"] != category:
            continue
        items.append(
            f"\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |"
        )
    table_en += "".join(items)
    path_prefix = category.upper() + "_" if category else ""
    with open(f"./{path_prefix}README.md", "w", encoding="utf-8") as f:
        f.write(
            category_readme_cn.format(
                category_dict.get(category, category), category.upper(), table_cn
            )
        )
    with open(f"./{path_prefix}README_EN.md", "w", encoding="utf-8") as f:
        f.write(category_readme_en.format(category, category.upper(), table_en))


def refresh(result):
    """update problems"""
    pattern = re.compile('src="(.*?)"')
    for question in result:
        front_question_id = question["frontend_question_id"]
        print(front_question_id)
        paid_only = " 🔒" if question["paid_only"] else ""

        path_cn = unquote(str(question["relative_path_cn"]).replace("/solution", "."))
        path_en = unquote(str(question["relative_path_en"]).replace("/solution", "."))

        with open(path_cn, "r", encoding="utf-8") as f1:
            cn_content = f1.read()
        with open(path_en, "r", encoding="utf-8") as f2:
            en_content = f2.read()

        category = question["category"]

        readme_template_cn, readme_template_en = select_templates(category)
        rating_item = rating_dict.get(str(front_question_id))
        rating = int(rating_item.get("Rating", 0)) if rating_item else ""
        source = (
            rating_item.get("ContestID_zh") + " " + rating_item.get("ProblemIndex")
            if rating_item
            else ""
        )
        cat = category_dict.get(category, category)
        cat = cat.title() if cat and cat[0].islower() else cat
        metadata = {
            "tags": question["tags_cn"] or [cat],
            "difficulty": question["difficulty_cn"],
            "rating": rating,
            "comments": True,
            "edit_url": f'https://github.com/doocs/leetcode/edit/main{question["relative_path_cn"]}',
            "source": source,
        }

        if (not question["tags_cn"] and not cat) or metadata["tags"] == ["Algorithms"]:
            metadata.pop("tags")
        if not rating:
            metadata.pop("rating")
        if not source:
            metadata.pop("source")
        yaml_metadata = yaml.dump(
            metadata, default_flow_style=False, allow_unicode=True
        )
        metadata_section = f"---\n{yaml_metadata}---"
        readme_template_cn = readme_template_cn.format(
            metadata_section,
            int(question["frontend_question_id"]),
            question["title_cn"].strip() + paid_only,
            question["url_cn"],
            question["relative_path_en"],
            question["content_cn"].replace("leetcode-cn.com", "leetcode.cn"),
        )
        cn_content = (
            readme_template_cn[: readme_template_cn.index("## 解法")]
            + cn_content[cn_content.index("## 解法") :]
        )

        source = (
            rating_item.get("ContestID_en") + " " + rating_item.get("ProblemIndex")
            if rating_item
            else ""
        )

        cat = category.title() if category and category[0].islower() else category
        metadata = {
            "tags": question["tags_en"] or [cat],
            "difficulty": question["difficulty_en"],
            "rating": rating,
            "comments": True,
            "edit_url": f'https://github.com/doocs/leetcode/edit/main{question["relative_path_en"]}',
            "source": source,
        }
        if (not question["tags_en"] and not [category]) or metadata["tags"] == [
            "Algorithms"
        ]:
            metadata.pop("tags")
        if not rating:
            metadata.pop("rating")
        if not source:
            metadata.pop("source")
        yaml_metadata = yaml.dump(
            metadata, default_flow_style=False, allow_unicode=True
        )
        metadata_section = f"---\n{yaml_metadata}---"
        readme_template_en = readme_template_en.format(
            metadata_section,
            int(question["frontend_question_id"]),
            question["title_en"].strip() + paid_only,
            question["url_en"],
            question["relative_path_cn"],
            question["content_en"],
        )
        en_content = (
            readme_template_en[: readme_template_en.index("## Solutions")]
            + en_content[en_content.index("## Solutions") :]
        )

        # replace image url to cdn link
        for url in pattern.findall(cn_content) or []:
            image_name = (
                os.path.basename(url).replace(".PNG", ".png").replace(".JPG", ".jpg")
            )
            new_url = (
                "https://fastly.jsdelivr.net/gh/doocs/leetcode@main"
                + str(question["relative_path_cn"]).replace("README.md", "images/")
                + image_name
            )
            cn_content = cn_content.replace(url, new_url)

        cn_content = cn_content.replace("leetcode-cn.com", "leetcode.cn")
        with open(path_cn, "w", encoding="utf-8") as f1:
            f1.write(cn_content)

        for url in pattern.findall(en_content) or []:
            image_name = (
                os.path.basename(url).replace(".PNG", ".png").replace(".JPG", ".jpg")
            )
            new_url = (
                "https://fastly.jsdelivr.net/gh/doocs/leetcode@main"
                + str(question["relative_path_cn"]).replace("README.md", "images/")
                + image_name
            )
            en_content = en_content.replace(url, new_url)

        with open(path_en, "w", encoding="utf-8") as f2:
            f2.write(en_content)


def generate_contest_readme(result: List):
    result.sort(key=lambda x: -x[0])
    content_cn = "\n\n".join(c[1] for c in result)
    content_en = "\n\n".join(c[2] for c in result)
    metadata_section = "---\ncomments: true\n---"
    content_cn = contest_readme_cn.format(metadata_section, content_cn)
    with open("./CONTEST_README.md", "w", encoding="utf-8") as f:
        f.write(content_cn)
    content_en = contest_readme_en.format(metadata_section, content_en)
    with open("./CONTEST_README_EN.md", "w", encoding="utf-8") as f:
        f.write(content_en)
