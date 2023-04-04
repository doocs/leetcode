import json
import time
from datetime import timedelta, timezone, datetime
from itertools import chain

import requests

weekly_range = range(83, 500)
biweekly_range = range(1, 300)
weekly_url = 'https://leetcode.cn/contest/api/info/weekly-contest-{}/'
biweekly_url = 'https://leetcode.cn/contest/api/info/biweekly-contest-{}/'

questions = {}


def format_time(timestamp) -> str:
    tz = timezone(timedelta(hours=+8))
    return datetime.fromtimestamp(timestamp, tz).strftime('%Y-%m-%d %H:%M')


def format_duration(seconds) -> str:
    m = seconds // 60
    return f'{m} 分钟'


def weekly(contest):
    url = weekly_url.format(contest)
    time.sleep(0.2)
    try:
        res = requests.get(url, timeout=6, verify=False).json()
        res['title'] = f'第 {contest} 场周赛'
        res['title_en'] = f'Weekly Contest {contest}'
        print(res)
        return res
    except Exception as e:
        print(str(e))


def biweekly(contest):
    url = biweekly_url.format(contest)
    time.sleep(0.2)
    try:
        res = requests.get(url, timeout=6, verify=False).json()
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
        if not v or 'contest' not in v:
            continue
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

    contest_readme_prefix = """# 力扣竞赛

[English Version](/solution/CONTEST_README_EN.md)


## 段位与荣誉勋章

竞赛排名根据竞赛积分（周赛和双周赛）进行计算，注册新用户的基础分值为 1500 分，在竞赛积分 &ge;1600 的用户中，根据比例 5%, 20%, 75% 设定三档段位，段位每周比赛结束后计算一次。

如果竞赛积分处于段位的临界值，在每周比赛结束重新计算后会出现段位升级或降级的情况。段位升级或降级后会自动替换对应的荣誉勋章。

| 段位  | 比例   | 段位名   | 国服分数线    | 勋章                                                                    |
| ----- | ------ | -------- | --------- | --------------------------------------------------------------------------- |
| LV3 | 5%  | Guardian | &ge;2251.88 | <p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/images/Guardian.gif" style="width: 80px;" /></p> |
| LV2 | 20% | Knight   | &ge;1879.80 | <p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/images/Knight.gif" style="width: 80px;" /></p>   |
| LV1 | 75% | -        | -         | -                                                                           |

力扣竞赛 **全国排名前 10** 的用户，全站用户名展示为品牌橙色。

## 赛后估分网站

- https://lcpredictor.herokuapp.com
- https://lccn.lbao.site

## 往期竞赛\n\n"""
    contest_readme_en_prefix = """# LeetCode Contest

[中文文档](/solution/CONTEST_README.md)

## Contest Rating & Badge

The contest badge is calculated based on the contest rating.

For LeetCoders with rating >=1600,
If you are in the top 5% of the contest rating, you’ll get the “Guardian” badge.

If you are in the top 25% of the contest rating, you’ll get the “Knight” badge.

| Level | Proportion | Badge      | Rating |                                                                                                                         |
| ----- | ---------- | ---------- | -------------- | ----------------------------------------------------------------------------------------------------------------------- |
| LV3 | 5\%      | Guardian | &ge;2228.90   | <p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/images/Guardian.gif" style="width: 80px;" /></p> |
| LV2 | 20\%     | Knight   | &ge;1842.73   | <p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/images/Knight.gif" style="width: 80px;" /></p>   |
| LV1 | 75\%     | -          | -              | -                                                                                                                       |

For top 10 users (excluding LCCN users), your LeetCode ID will be colored orange on the ranking board. You'll also have the honor with you when you post/comment under discuss.

## Rating Predictor

Get your rating changes right after the completion of LeetCode contests, https://lcpredictor.herokuapp.com

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
