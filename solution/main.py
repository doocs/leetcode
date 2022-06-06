import json
import os
import re
from urllib.parse import quote, unquote

from spider import Spider

# load templates
with open('./readme_template.md', 'r', encoding='utf-8') as f:
    readme_cn = f.read()
with open('./readme_template_en.md', 'r', encoding='utf-8') as f:
    readme_en = f.read()

with open('./problem_readme_template.md', 'r', encoding='utf-8') as f:
    problem_readme_cn = f.read()
with open('./problem_readme_template_en.md', 'r', encoding='utf-8') as f:
    problem_readme_en = f.read()
with open('./sql_problem_readme_template.md', 'r', encoding='utf-8') as f:
    sql_readme_cn = f.read()
with open('./sql_problem_readme_template_en.md', 'r', encoding='utf-8') as f:
    sql_readme_en = f.read()
with open('./bash_problem_readme_template.md', 'r', encoding='utf-8') as f:
    bash_readme_cn = f.read()
with open('./bash_problem_readme_template_en.md', 'r', encoding='utf-8') as f:
    bash_readme_en = f.read()


def select_templates(category):
    if category == 'Shell':
        return [bash_readme_cn, bash_readme_en]
    if category == 'Database':
        return [sql_readme_cn, sql_readme_en]
    return [problem_readme_cn, problem_readme_en]


def generate_readme(result):
    md_table_cn = [item['md_table_row_cn'] for item in result]
    md_table_en = [item['md_table_row_en'] for item in result]

    # generate README.md
    items = []
    table_cn = '\n|  题号  |  题解  |  标签  |  难度  | 备注 |\n| --- | --- | --- | --- | --- |'
    for item in sorted(md_table_cn, key=lambda x: x[0]):
        items.append(
            f'\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |'
        )
    table_cn += ''.join(items)

    # generate README_EN.md
    items = []
    table_en = '\n|  #  |  Solution  |  Tags  |  Difficulty  |  Remark |\n| --- | --- | --- | --- | --- |'
    for item in sorted(md_table_en, key=lambda x: x[0]):
        items.append(
            f'\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |'
        )
    table_en += ''.join(items)

    with open('./README.md', 'w', encoding='utf-8') as f:
        f.write(readme_cn.format(table_cn))
    with open('./README_EN.md', 'w', encoding='utf-8') as f:
        f.write(readme_en.format(table_en))


def generate_question_readme(result):
    for item in result:
        if not item['content_cn'] and not item['content_en']:
            continue
        path = (
            f'./{item["sub_folder"]}/{item["frontend_question_id"]}.{item["title_en"]}'
        )
        path = path.replace(":", " ")
        if os.path.isdir(path):
            continue
        os.makedirs(path)

        # choose the readme template
        category = item['category']
        readme_template_cn, readme_template_en = select_templates(category)

        # generate lc-cn problem readme
        with open(f'{path}/README.md', 'w', encoding='utf-8') as f1:
            f1.write(
                readme_template_cn.format(
                    int(item['frontend_question_id']),
                    item["title_cn"],
                    item['url_cn'],
                    item['relative_path_en'],
                    item['content_cn'],
                )
            )

        # generate lc-en problem readme
        with open(f'{path}/README_EN.md', 'w', encoding='utf-8') as f2:
            f2.write(
                readme_template_en.format(
                    int(item['frontend_question_id']),
                    item["title_en"],
                    item['url_en'],
                    item['relative_path_cn'],
                    item['content_en'],
                )
            )


def generate_summary(result):
    """generate summary files"""
    summary_cn = summary_en = ''
    m = {int(item['frontend_question_id']): item for item in result}
    for file in sorted(os.listdir("./"), key=lambda x: x.lower()):
        if os.path.isdir("./" + file) and file != '__pycache__':
            summary_cn += f'\n- {file}\n'
            summary_en += f'\n- {file}\n'
            for sub in sorted(os.listdir('./' + file), key=lambda x: x.lower()):
                sub = sub.replace('`', ' ')
                enc = quote(sub)

                data = m.get(int(sub[:4]))
                sub_cn = sub
                if data:
                    sub_cn = sub[:5] + data['title_cn']

                summary_cn += f'  - [{sub_cn}](/solution/{file}/{enc}/README.md)\n'
                summary_en += f'  - [{sub}](/solution/{file}/{enc}/README_EN.md)\n'

    # generate summary.md
    with open('./summary.md', 'w', encoding='utf-8') as f:
        f.write(summary_cn)

    # generate summary_en.md
    with open('./summary_en.md', 'w', encoding='utf-8') as f:
        f.write(summary_en)


def refresh(result):
    """update problems"""
    pattern = re.compile("src=\"(.*?)\"")

    for question in result:
        front_question_id = question['frontend_question_id']
        print(front_question_id)
        title = question['title_cn']
        title_en = question['title_en']

        path_cn = unquote(str(question['relative_path_cn']).replace("/solution", "."))
        path_en = unquote(str(question['relative_path_en']).replace("/solution", "."))

        with open(path_cn, 'r', encoding='utf-8') as f1:
            cn_content = f1.read()

        # update title
        with open(path_en, 'r', encoding='utf-8') as f2:
            en_content = f2.read()
        i = cn_content.index('. ')
        j = cn_content.index(']')
        cn_content = cn_content.replace(cn_content[i + 2 : j], title)
        i = en_content.index('. ')
        j = en_content.index(']')
        en_content = en_content.replace(en_content[i + 2 : j], title_en)

        # update question content
        old_content = re.search("<!-- 这里写题目描述 -->(.*?)## 解法", cn_content, re.S).group(1)
        cn_content = cn_content.replace(
            old_content, "\n\n" + question['content_cn'] + "\n\n"
        ).replace("\n\n    <ul>", "\n    <ul>")

        # replace image url to cdn link
        for url in pattern.findall(cn_content) or []:
            image_name = (
                os.path.basename(url).replace('.PNG', '.png').replace('.JPG', '.jpg')
            )
            new_url = (
                'https://fastly.jsdelivr.net/gh/doocs/leetcode@main'
                + str(question['relative_path_cn']).replace("README.md", "images/")
                + image_name
            )
            cn_content = cn_content.replace(url, new_url)

        with open(path_cn, 'w', encoding='utf-8') as f1:
            f1.write(cn_content)

        old_content = re.search(
            "## Description(.*?)## Solutions", en_content, re.S
        ).group(1)
        en_content = en_content.replace(
            old_content, "\n\n" + question['content_en'] + "\n\n"
        ).replace("\n\n    <ul>", "\n    <ul>")

        for url in pattern.findall(en_content) or []:
            image_name = (
                os.path.basename(url).replace('.PNG', '.png').replace('.JPG', '.jpg')
            )
            new_url = (
                'https://fastly.jsdelivr.net/gh/doocs/leetcode@main'
                + str(question['relative_path_cn']).replace("README.md", "images/")
                + image_name
            )
            en_content = en_content.replace(url, new_url)

        with open(path_en, 'w', encoding='utf-8') as f2:
            f2.write(en_content)


def save(result):
    with open('./result.json', 'w', encoding='utf-8') as f:
        f.write(json.dumps(result))


if __name__ == '__main__':
    cookie_cn = ''
    cookie_en = ''
    spider = Spider(cookie_cn, cookie_en)
    res = spider.run()
    save(res)

    with open('./result.json', 'r', encoding='utf-8') as f:
        res = f.read()
        res = json.loads(res)

    generate_readme(res)
    generate_question_readme(res)
    generate_summary(res)
    # refresh(res)
