import json
import os
import re
from typing import Tuple, List
from urllib.parse import quote, unquote


def load_template(template_name: str) -> str:
    with open("./template.md", "r", encoding="utf-8") as f:
        content = f.read()
        start_text = f"<!-- 这里是{template_name}开始位置 -->"
        end_text = f"<!-- 这里是{template_name}结束位置 -->"
        content = re.search(f"{start_text}(.*?){end_text}", content, re.S).group(1)
        return content.strip()


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

category_dict = {
    'Database': '数据库',
}


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

        # generate lc-cn problem readme
        with open(f"{path}/README.md", "w", encoding="utf-8") as f1:
            f1.write(
                readme_template_cn.format(
                    int(item["frontend_question_id"]),
                    item["title_cn"],
                    item["url_cn"],
                    item["relative_path_en"],
                    item["content_cn"].replace("leetcode-cn.com", "leetcode.cn"),
                )
            )

        # generate lc-en problem readme
        with open(f"{path}/README_EN.md", "w", encoding="utf-8") as f2:
            f2.write(
                readme_template_en.format(
                    int(item["frontend_question_id"]),
                    item["title_en"],
                    item["url_en"],
                    item["relative_path_cn"],
                    item["content_en"],
                )
            )


def generate_summary(result):
    """generate summary files"""
    summary_cn = summary_en = ""
    m = {int(item["frontend_question_id"]): item for item in result}
    for file in sorted(os.listdir("./"), key=lambda x: x.lower()):
        if os.path.isdir("./" + file) and file != "__pycache__":
            summary_cn += f"\n- {file}\n"
            summary_en += f"\n- {file}\n"
            for sub in sorted(os.listdir("./" + file), key=lambda x: x.lower()):
                sub = sub.replace("`", " ")
                enc = quote(sub)
                if not sub[:4].isdigit():
                    continue
                data = m.get(int(sub[:4]))
                sub_cn = sub
                if data:
                    sub_cn = sub[:5] + data["title_cn"]

                summary_cn += f"  - [{sub_cn}](/solution/{file}/{enc}/README.md)\n"
                summary_en += f"  - [{sub}](/solution/{file}/{enc}/README_EN.md)\n"

    # generate summary.md
    with open("./summary.md", "w", encoding="utf-8") as f:
        f.write(summary_cn)

    # generate summary_en.md
    with open("./summary_en.md", "w", encoding="utf-8") as f:
        f.write(summary_en)


def generate_category_summary(result, category=""):
    """generate category summary files"""
    summary_cn = (
        "- " + category_dict.get(category, category) + "专项练习\n\n" if category else ""
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
        title = question["title_cn"]
        title_en = question["title_en"]

        path_cn = unquote(str(question["relative_path_cn"]).replace("/solution", "."))
        path_en = unquote(str(question["relative_path_en"]).replace("/solution", "."))

        with open(path_cn, "r", encoding="utf-8") as f1:
            cn_content = f1.read()

        # update title
        with open(path_en, "r", encoding="utf-8") as f2:
            en_content = f2.read()
        i = cn_content.index(". ")
        j = cn_content.index("]")
        cn_content = cn_content.replace(cn_content[i + 2 : j], title)
        i = en_content.index(". ")
        j = en_content.index("]")
        en_content = en_content.replace(en_content[i + 2 : j], title_en)

        # update question content
        old_content = re.search("<!-- 这里写题目描述 -->(.*?)## 解法", cn_content, re.S).group(1)
        if question.get("content_cn"):
            cn_content = cn_content.replace(
                old_content, "\n\n" + question["content_cn"] + "\n\n"
            ).replace("\n\n    <ul>", "\n    <ul>")

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

        old_content = re.search(
            "## Description(.*?)## Solutions", en_content, re.S
        ).group(1)
        if question.get("content_en"):
            en_content = en_content.replace(
                old_content, "\n\n" + question["content_en"] + "\n\n"
            ).replace("\n\n    <ul>", "\n    <ul>")

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
    content_cn = contest_readme_cn.format(content_cn)
    with open("./CONTEST_README.md", "w", encoding="utf-8") as f:
        f.write(content_cn)
    content_en = contest_readme_en.format(content_en)
    with open("./CONTEST_README_EN.md", "w", encoding="utf-8") as f:
        f.write(content_en)
