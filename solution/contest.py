import json
import time
from itertools import chain

import requests

weekly_range = range(83, 500)
biweekly_range = range(1, 300)
weekly_url = 'https://leetcode.cn/contest/api/info/weekly-contest-{}/'
biweekly_url = 'https://leetcode.cn/contest/api/info/biweekly-contest-{}/'

questions = {}


def format_time(timestamp):
    return time.strftime('%Y-%m-%d %H:%M', time.localtime(timestamp))


def format_duration(seconds):
    m = seconds // 60
    return f'{m} 分钟'


def weekly(contest):
    url = weekly_url.format(contest)
    time.sleep(1)
    try:
        res = requests.get(url, timeout=6).json()
        res['title'] = f'第 {contest} 场周赛'
        res['title_en'] = f'Weekly Contest {contest}'
        print(res)
        return res
    except Exception as e:
        print(str(e))


def biweekly(contest):
    url = biweekly_url.format(contest)
    time.sleep(1)
    try:
        res = requests.get(url, timeout=6).json()
        res['title'] = f'第 {contest} 场双周赛'
        res['title_en'] = f'Biweekly Contest {contest}'
        print(res)
        return res
    except Exception as e:
        print(str(e))


def handle(result: dict):
    if result is None or 'error' in result:
        return None
    qs = result['questions']
    if qs is None:
        return None
    for q in qs:
        questions[q['title_slug']] = {
            'contest_title': result['title'],
            'contest_title_en': result['title_en'],
            'contest_title_slug': result['contest']['title_slug'],
            'contest_id': result['contest']['id'],
            'contest_start_time': result['contest']['origin_start_time'],
            'contest_duration': result['contest']['duration'],
            'user_num': result['user_num'],
        }


def run():
    weekly_res = []
    biweekly_res = []
    contest_list = []
    for i in weekly_range:
        res = weekly(i)
        if res and 'error' in res:
            break
        weekly_res.append(res)
    for i in biweekly_range:
        res = biweekly(i)
        if res and 'error' in res:
            break
        biweekly_res.append(res)
    for v in chain(weekly_res, biweekly_res):
        handle(v)
        contest_list.append(
            [
                v['contest']['id'],
                v['title'],
                v['title_en'],
                v['questions'],
                v['contest']['start_time'],
                v['contest']['duration'],
                v['user_num'],
            ]
        )

    contest_list.sort(key=lambda x: x[4], reverse=True)
    with open("contest.json", 'w', encoding='utf-8') as f:
        f.write(json.dumps(questions))
    with open('contest_list.json', 'w', encoding='utf-8') as f:
        f.write(json.dumps(contest_list))


# ["id", "title", "title_en", "questions", "start_time", "duration"]


def generate_contest_list():
    with open('./result.json', 'r', encoding='utf-8') as f:
        result = json.loads(f.read())
        m = {item['question_title_slug']: item for item in result}
    with open('contest_list.json', 'r', encoding='utf-8') as f:
        contest_list = list(json.loads(f.read()))

    contest_readme_prefix = """# 力扣竞赛\n\n[English Version](/solution/CONTEST_README_EN.md)\n\n
## 赛后估分网站\n\nhttps://lcpredictor.herokuapp.com/\n\n## 往期竞赛\n\n"""
    contest_readme_en_prefix = """# LeetCode Contest\n\n[中文文档](/solution/CONTEST_README.md)\n\n
## Rating Predictor\n
Get your rating changes right after the completion of LeetCode contests, https://lcpredictor.herokuapp.com/\n
## Past Contests\n\n"""

    items = []
    en_items = []

    for _, title, title_en, qs, start_time, duration, user_num in contest_list:
        v = (
            "#### "
            + title
            + f'({format_time(start_time) + ", " + format_duration(duration)}) '
            + f'参赛人数 {user_num}'
            + "\n\n"
        )
        v_en = "#### " + title_en + "\n\n"
        for q in qs:
            slug = q['title_slug']
            data = m.get(slug)
            if data is None:
                continue
            v += f'- [{str(int(data["frontend_question_id"])) + ". " + data["title_cn"]}]({data["relative_path_cn"]})\n'
            v_en += f'- [{str(int(data["frontend_question_id"])) + ". " + data["title_en"]}]({data["relative_path_en"]})\n'
        if qs:
            items.append(v)
            en_items.append(v_en)
    content_cn = contest_readme_prefix + "\n\n".join(items)
    content_en = contest_readme_en_prefix + "\n\n".join(en_items)
    with open("CONTEST_README.md", 'w', encoding='utf-8') as f:
        f.write(content_cn)
    with open("CONTEST_README_EN.md", 'w', encoding='utf-8') as f:
        f.write(content_en)


run()
generate_contest_list()
