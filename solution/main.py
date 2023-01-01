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
    cookie_cn = 'gr_user_id=7b94d263-adfc-4e00-90e5-f980bf076eed; csrftoken=pOMbXbvcVSqNKAaq8fhn1rgvYuPoPEBS1iJ4EC8jp8cPGqvcPoadtU9lTaHhvW21; a2873925c34ecbd2_gr_last_sent_cs1=lcbin; p_h5_u=404F51F8-3F22-4F82-9D76-B7E13CBEF3CE; selectedStreamLevel=HD; __atuvc=0|39,0|40,0|41,0|42,9|43; _bl_uid=vClzU9L3pyL0sFj7025nbLe3C8mX; _gid=GA1.2.1428750873.1672015943; cf_clearance=bQh04yiBHSknIdFoYPuUxUVTjqR_UnAuLoDhbrXASaw-1672370774-0-160; Hm_lvt_fa218a3ff7179639febdb15e372f411c=1672300277,1672393046,1672449807,1672485292; Hm_lvt_f0faad39bcf8471e3ab3ef70125152c3=1672469061,1672539807,1672540097,1672544860; _gat_gtag_UA_131851415_1=1; aliyungf_tc=32159deca782532138ea6c5c0d3b6c4c88841da791d8853c1cc7bd73c266e009; a2873925c34ecbd2_gr_session_id=82c6187e-e4df-4955-a51b-086a76656532; a2873925c34ecbd2_gr_last_sent_sid_with_cs1=82c6187e-e4df-4955-a51b-086a76656532; a2873925c34ecbd2_gr_session_id_82c6187e-e4df-4955-a51b-086a76656532=true; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuZXh0X2FmdGVyX29hdXRoIjoiLyIsIl9hdXRoX3VzZXJfaWQiOiIyNTQwMyIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiYTkwOWQwNTQzODU2NDgyYzhhNTA3ZDhiYjY4OTdlNTM1Y2EyZGYyOTU2ZTc5MzBiZjMwNzViMGMyZTNmYWIzZiIsImlkIjoyNTQwMywiZW1haWwiOiJjb250YWN0QHlhbmdsaWJpbi5pbmZvIiwidXNlcm5hbWUiOiJsY2JpbiIsInVzZXJfc2x1ZyI6ImxjYmluIiwiYXZhdGFyIjoiaHR0cHM6Ly9hc3NldHMubGVldGNvZGUuY24vYWxpeXVuLWxjLXVwbG9hZC91c2Vycy9iaW5nb29vL2F2YXRhcl8xNTIyNjQ5NDc3LnBuZyIsInBob25lX3ZlcmlmaWVkIjp0cnVlLCJfdGltZXN0YW1wIjoxNjcxMTA4ODMwLjA1OTYzNCwiZXhwaXJlZF90aW1lXyI6MTY3MzYzNjQwMCwidmVyc2lvbl9rZXlfIjowLCJsYXRlc3RfdGltZXN0YW1wXyI6MTY3MjU0NDg2MH0.2mTlBEAH_55kxFiRusp-rlS_Jzof36dGgV0VD1GqCFs; Hm_lpvt_f0faad39bcf8471e3ab3ef70125152c3=1672544916; _ga_PDVPZYN3CW=GS1.1.1672544860.34.1.1672544916.0.0.0; _ga=GA1.2.39301968.1671108818; a2873925c34ecbd2_gr_cs1=lcbin'
    cookie_en = 'gr_user_id=726c2dd2-240d-4971-b0f5-37744121657c; 87b5a3c3f1a55520_gr_last_sent_cs1=bingooo; _ga_DKXQ03QCVK=GS1.1.1628508904.1.1.1628508911.53; __stripe_mid=06148a1f-c255-4f92-9dc2-3b44dec7ef05c9325f; __atuvc=0|40,0|41,0|42,1|43,2|44; csrftoken=VtIXu4RRj643YogcwVe9UM31JoEFnEQeQaVhFADQ5yFTkhkU0TT3HM1Xe6PxYyCX; _ga=GA1.1.848882851.1671189525; _ga_CDRWKZTDEX=GS1.1.1672281807.3.0.1672281889.0.0.0; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiMTE4NzEwNyIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImFsbGF1dGguYWNjb3VudC5hdXRoX2JhY2tlbmRzLkF1dGhlbnRpY2F0aW9uQmFja2VuZCIsIl9hdXRoX3VzZXJfaGFzaCI6ImEyM2FkMWMyOTBhNDY1MTliNDg2YjMwNjljNDFjMjg3ZjVhYzI4YWUiLCJpZCI6MTE4NzEwNywiZW1haWwiOiJjb250YWN0QHlhbmdsaWJpbi5pbmZvIiwidXNlcm5hbWUiOiJiaW5nb29vIiwidXNlcl9zbHVnIjoiYmluZ29vbyIsImF2YXRhciI6Imh0dHBzOi8vYXNzZXRzLmxlZXRjb2RlLmNvbS91c2Vycy9iaW5nb29vL2F2YXRhcl8xNTIyNjgxMzQwLnBuZyIsInJlZnJlc2hlZF9hdCI6MTY3MjU0NDkzNywiaXAiOiIxMDMuMTUyLjIyMC4xOCIsImlkZW50aXR5IjoiYTgxOGFiMzU5ODA0NTE3ZjI1NDllOTRjODhkMDNjMGIiLCJzZXNzaW9uX2lkIjozMjMxMjA3NH0.UUo59hhOPJdURA4lAzR5fI3HDvQeq-zff08_B5lgSXk; _dd_s=rum=1&id=0840fa9b-a8a9-41fb-b996-1ca09836171a&created=1672544939251&expire=1672545839251'
    spider = Spider(cookie_cn, cookie_en)
    res = spider.run()
    save(res)

    with open('./result.json', 'r', encoding='utf-8') as f:
        res = f.read()
        res = json.loads(res)

    generate_readme(res)
    generate_question_readme(res)
    generate_summary(res)
    refresh(res)
