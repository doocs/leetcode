import json
from datetime import timedelta, timezone, datetime

import requests
import urllib3

urllib3.disable_warnings()

weekly_range = range(83, 500)
biweekly_range = range(1, 300)
WEEKLY_URL = 'https://leetcode.cn/contest/api/info/weekly-contest-{}/'
BIWEEKLY_URL = 'https://leetcode.cn/contest/api/info/biweekly-contest-{}/'

with open('./result.json', 'r', encoding='utf-8') as f:
    # load all question details <question_title_slug: detail>
    data = json.loads(f.read())
    question_details = {item['question_title_slug']: item for item in data}


class Contest:
    def __init__(self, contest_seq: int, contest_type: int = 1):
        double = contest_type % 2 == 0
        url_pattern = BIWEEKLY_URL if double else WEEKLY_URL
        self.contest_type = contest_type
        self.contest_url = url_pattern.format(contest_seq)
        self.contest_title = f'第 {contest_seq} 场双周赛' if double else f'第 {contest_seq} 场周赛'
        self.contest_title_en = f'Biweekly Contest {contest_seq}' if double else f'Weekly Contest {contest_seq}'

    @staticmethod
    def format_time(timestamp: int) -> str:
        tz = timezone(timedelta(hours=+8))
        return datetime.fromtimestamp(timestamp, tz).strftime('%Y-%m-%d %H:%M')

    def get_data(self):
        res = requests.get(self.contest_url, timeout=6, verify=False).json()
        if not res or 'error' in res or not res['questions']:
            return {}
        questions = res['questions']
        question_slugs = [q['title_slug'] for q in questions]
        return {
            'contest_title': self.contest_title,
            'contest_title_en': self.contest_title_en,
            'contest_title_slug': res['contest']['title_slug'],
            'contest_id': res['contest']['id'],
            'contest_start_time': res['contest']['origin_start_time'],
            'contest_duration': res['contest']['duration'],
            'user_num': res['user_num'],
            'question_slugs': question_slugs
        }

    def format(self, data: dict):
        if not data:
            return []
        title = data['contest_title']
        title_en = data['contest_title_en']
        start_time = data['contest_start_time']
        duration = data['contest_duration']
        cost_minutes = duration // 60
        user_num = data['user_num']
        rows = [f'#### {title}({Contest.format_time(start_time)}, {cost_minutes} 分钟) 参赛人数 {user_num}\n']
        rows_en = [f'#### {title_en}\n']
        for question_slug in data['question_slugs']:
            detail = question_details.get(question_slug, {})
            if not detail:
                continue
            frontend_question_id = detail['frontend_question_id']
            question_title = detail['title_cn']
            question_title_en = detail['title_en']
            question_relative_path = detail['relative_path_cn']
            question_relative_path_en = detail['relative_path_en']
            rows.append(f'- [{frontend_question_id}. {question_title}]({question_relative_path})')
            rows_en.append(f'- [{frontend_question_id}. {question_title_en}]({question_relative_path_en})')
        return [
            self.contest_type,
            start_time,
            '\n'.join(rows),
            '\n'.join(rows_en)
        ]

    def run(self):
        res = self.format(self.get_data())
        print(res)
        return res


def run():
    all_contests = [Contest(i) for i in weekly_range] + [Contest(i, 2) for i in biweekly_range]
    results = []
    cnt = 0
    for contest in all_contests:
        res = contest.run()
        if res:
            results.append(res)
        elif (cnt := cnt + 1) > 3:
            break
    results.sort(key=lambda x: -x[1])
    content_cn = '\n\n'.join(result[2] for result in results)
    content_en = '\n\n'.join(result[3] for result in results)

    with open('./contest_readme_template.md', 'r', encoding='utf-8') as f:
        content_cn = f.read().format(content_cn)
    with open('./CONTEST_README.md', 'w', encoding='utf-8') as f:
        f.write(content_cn)
    with open('./contest_readme_template_en.md', 'r', encoding='utf-8') as f:
        content_en = f.read().format(content_en)
    with open('./CONTEST_README_EN.md', 'w', encoding='utf-8') as f:
        f.write(content_en)


run()
