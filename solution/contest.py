import json
import time

import requests

weekly_range = range(83, 500)
biweekly_range = range(1, 300)
weekly_url = 'https://leetcode.cn/contest/api/info/weekly-contest-{}/'
biweekly_url = 'https://leetcode.cn/contest/api/info/biweekly-contest-{}/'

questions = {}


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
            'contest_title_slug': result['contest']['title_slug']
        }


def run():
    weekly_res = []
    biweekly_res = []
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
    for v in weekly_res + biweekly_res:
        handle(v)
    with open("contest.json", 'w', encoding='utf-8') as f:
        f.write(json.dumps(questions))


run()
