import json
import os.path
import re
from urllib.parse import unquote

with open(
    './result.json',
    'r',
    encoding='utf-8',
) as f:
    data = json.loads(f.read())

pattern = re.compile("src=\"(.*?)\"")

for question in data:
    front_question_id = question['frontend_question_id']
    print(question)

    path_cn = unquote(str(question['relative_path_cn']).replace("/solution", "."))
    path_en = unquote(str(question['relative_path_en']).replace("/solution", "."))

    with open(path_cn, 'r', encoding='utf-8') as f1:
        cn_content = f1.read()

    with open(path_en, 'r', encoding='utf-8') as f2:
        en_content = f2.read()

    old_content = re.search("<!-- 这里写题目描述 -->(.*?)## 解法", cn_content, re.S).group(1)
    cn_content = cn_content.replace(
        old_content, "\n\n" + question['content_cn'] + "\n\n"
    ).replace("\n\n    <ul>", "\n    <ul>")

    for url in pattern.findall(cn_content) or []:
        image_name = os.path.basename(url)
        new_url = (
            'https://cdn.jsdelivr.net/gh/doocs/leetcode@main'
            + str(question['relative_path_cn']).replace("README.md", "images/")
            + image_name
        )
        cn_content = cn_content.replace(url, new_url)

    with open(path_cn, 'w', encoding='utf-8') as f1:
        f1.write(cn_content)

    old_content = re.search("## Description(.*?)## Solutions", en_content, re.S).group(
        1
    )
    en_content = en_content.replace(
        old_content, "\n\n" + question['content_en'] + "\n\n"
    ).replace("\n\n    <ul>", "\n    <ul>")

    for url in pattern.findall(en_content) or []:
        image_name = os.path.basename(url)
        new_url = (
            'https://cdn.jsdelivr.net/gh/doocs/leetcode@main'
            + str(question['relative_path_cn']).replace("README.md", "images/")
            + image_name
        )
        en_content = en_content.replace(url, new_url)

    with open(path_en, 'w', encoding='utf-8') as f2:
        f2.write(en_content)

# format documents: prettier --write "**/*.md"
