import json
import os
import re
from urllib.parse import quote

import requests


def generate_number_mapper() -> dict:
    numbers = [i for i in range(100)]
    number_mapper = dict()
    for number in numbers:
        start = number * 100
        end = start + 99
        subdir = str(start).zfill(4) + '-' + str(end).zfill(4)
        number_mapper[str(number)] = subdir
    return number_mapper


def generate_difficulty_mapper() -> dict:
    return {
        'Easy': '简单',
        'Medium': '中等',
        'Hard': '困难'
    }


def generate_difficulty_level_mapper() -> dict:
    return {
        '1': '简单',
        '2': '中等',
        '3': '困难'
    }


def generate_difficulty_level_mapper_en() -> dict:
    return {
        '1': 'Easy',
        '2': 'Medium',
        '3': 'Hard'
    }


class LCSpider:
    graph_url = 'https://leetcode-cn.com/graphql'
    user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) ' \
                 'Chrome/77.0.3865.120 Safari/537.36'

    def __init__(self):
        self.session = requests.session()

        # mapper
        self.number_mapper = generate_number_mapper()
        self.difficulty_mapper = generate_difficulty_mapper()
        self.difficulty_level_mapper = generate_difficulty_level_mapper()
        self.difficulty_level_mapper_en = generate_difficulty_level_mapper_en()

        # result
        self.result = []
        self.md_table_cn = []
        self.md_table_en = []

    def get_question_detail(self, question_title_slug):
        """获取题目详情"""
        form_data = {
            'operationName': 'globalData',
            'query': 'query globalData {\n  feature {\n    questionTranslation\n    subscription\n    signUp\n    '
                     'discuss\n    mockInterview\n    contest\n    store\n    book\n    chinaProblemDiscuss\n    '
                     'socialProviders\n    studentFooter\n    cnJobs\n    enableLsp\n    enableWs\n    '
                     'enableDebugger\n    enableDebuggerAdmin\n    enableDarkMode\n    tasks\n    '
                     'leetbook\n    __typename\n  }\n  userStatus {\n    isSignedIn\n    isAdmin\n    '
                     'isStaff\n    isSuperuser\n    isTranslator\n    isPremium\n    isVerified\n    '
                     'isPhoneVerified\n    isWechatVerified\n    checkedInToday\n    username\n    '
                     'realName\n    userSlug\n    groups\n    avatar\n    optedIn\n    '
                     'requestRegion\n    region\n    activeSessionId\n    permissions\n    notificationStatus {\n      '
                     'lastModified\n      numUnread\n      __typename\n    }\n    completedFeatureGuides\n    '
                     'useTranslation\n    accountStatus {\n      isFrozen\n      inactiveAfter\n      __typename\n    '
                     '}\n    __typename\n  }\n  siteRegion\n  chinaHost\n  websocketUrl\n  userBannedInfo {\n    '
                     'bannedData {\n      endAt\n      bannedType\n      __typename\n    }\n    __typename\n  }\n}\n',
            'variables': {}
        }
        headers = {
            'User-Agent': LCSpider.user_agent,
            'Connection': 'keep-alive',
            'Content-Type': 'application/json',
            'Referer': 'https://leetcode-cn.com/problems/' + question_title_slug}
        self.session.post(LCSpider.graph_url, data=json.dumps(form_data), headers=headers, timeout=10)

        form_data = {
            'operationName': 'questionData',
            'variables': {
                'titleSlug': question_title_slug
            },
            'query': """query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    
            questionId\n    questionFrontendId\n    boundTopicId\n    title\n    titleSlug\n    content\n    
            translatedTitle\n    translatedContent\n    isPaidOnly\n    difficulty\n    likes\n    dislikes\n    
            isLiked\n    similarQuestions\n    contributors {\n      username\n      profileUrl\n      
            avatarUrl\n      __typename\n    }\n    langToValidPlayground\n    topicTags {\n      name\n      
            slug\n      translatedName\n      __typename\n    }\n    companyTagStats\n    codeSnippets {\n      
            lang\n      langSlug\n      code\n      __typename\n    }\n    stats\n    hints\n    solution {\n      
            id\n      canSeeDetail\n      __typename\n    }\n    status\n    sampleTestCase\n    metaData\n    
            judgerAvailable\n    judgeType\n    mysqlSchemas\n    enableRunCode\n    envInfo\n    book {\n      
            id\n      bookName\n      pressName\n      source\n      shortDescription\n      fullDescription\n      
            bookImgUrl\n      pressImgUrl\n      productUrl\n      __typename\n    }\n    isSubscribed\n    
            isDailyQuestion\n    dailyRecordStatus\n    editorType\n    ugcQuestionId\n    style\n    
            __typename\n  }\n}\n"""
        }

        # 获取题目详情
        resp = self.session.post(url=LCSpider.graph_url, data=json.dumps(form_data).encode('utf-8'), headers=headers,
                                 timeout=10)
        res = resp.json()
        return res['data']['question']

    def get_all_questions(self):
        """获取所有题目"""
        headers = {
            'accept': 'application/json, text/javascript, */*; q=0.01',
            'content-type': 'application/json',
            'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/77.0.3865.120 Safari/537.36',
            'x-requested-with': 'XMLHttpRequest'
        }
        resp = self.session.get(url='https://leetcode.com/api/problems/all/', headers=headers, allow_redirects=False)
        questions = resp.json()['stat_status_pairs']

        for question in questions:
            frontend_question_id = str(question['stat']['frontend_question_id']).zfill(4)
            question_id = str(question['stat']['question_id']).zfill(4)
            no = int(frontend_question_id) // 100
            paid_only = question['paid_only']

            question_title_en = question['stat']['question__title']
            question_title_en = re.sub(r'[\\/:*?"<>|]', '', question_title_en).strip()
            question_title_slug = question['stat']['question__title_slug']

            url_cn = 'https://leetcode-cn.com/problems/' + question_title_slug
            url_en = 'https://leetcode.com/problems/' + question_title_slug

            path_cn = '/solution/{}/{}.{}/README.md'.format(self.number_mapper.get(str(no)), frontend_question_id,
                                                            quote(question_title_en))
            path_en = '/solution/{}/{}.{}/README_EN.md'.format(self.number_mapper.get(str(no)), frontend_question_id,
                                                               quote(question_title_en))
            print(frontend_question_id)
            item = {
                'question_id': question_id,
                'frontend_question_id': frontend_question_id,
                'paid_only': paid_only,
                'paid_only_cn': '',
                'url_cn': url_cn,
                'url_en': url_en,
                'relative_path_cn': path_cn,
                'relative_path_en': path_en,
                'title_cn': '',
                'title_en': question_title_en,
                'question_title_slug': question_title_slug,
                'content_en': '',
                'content_cn': '',
                'tags_en': [],
                'tags_cn': [],
                'difficulty_en': '',
                'difficulty_cn': '',
                'code_snippets': []
            }

            question_detail = self.get_question_detail(question_title_slug)
            if question_detail is not None:
                topic_tags = question_detail.get('topicTags')
                tags_cn = [e['translatedName'] for e in topic_tags if e['translatedName']]
                tags_en = [e['name'] for e in topic_tags if e['name']]
                item['title_cn'] = question_detail.get('translatedTitle')
                item['content_en'] = question_detail.get('content')
                item['content_cn'] = question_detail.get('translatedContent')
                item['tags_en'] = tags_en
                item['tags_cn'] = tags_cn
                item['difficulty_en'] = question_detail.get('difficulty')
                item['difficulty_cn'] = self.difficulty_mapper.get(question_detail.get('difficulty'))
                item['code_snippets'] = question_detail.get('codeSnippets')
                item['paid_only_cn'] = question_detail.get('isPaidOnly')
                if not item['content_en'] or not item['content_cn']:
                    continue
            self.result.append(item)
            col1_cn = f'[{frontend_question_id}]({url_cn})'
            col2_cn = f'[{item["title_cn"]}]({path_cn})'
            col3_cn = ','.join([f'`{tag}`' for tag in item['tags_cn']])
            col3_cn = '' if (col3_cn == 'None' or not col3_cn) else col3_cn
            col4_cn = item['difficulty_cn']
            col5_cn = '🔒' if item['paid_only_cn'] else ''
            col1_en = f'[{frontend_question_id}]({url_en})'
            col2_en = f'[{item["title_en"]}]({path_en})'
            col3_en = ','.join([f'`{tag}`' for tag in item['tags_en']])
            col3_en = '' if (col3_en == 'None' or not col3_en) else col3_en
            col4_en = item['difficulty_en']
            col5_en = '🔒' if paid_only else ''
            self.md_table_cn.append((col1_cn, col2_cn, col3_cn, col4_cn, col5_cn))
            self.md_table_en.append((col1_en, col2_en, col3_en, col4_en, col5_en))

    def save_result(self):
        with open('result.json', 'a', encoding='utf-8') as f:
            f.write(json.dumps(self.result))

    def generate_md_table(self):
        """生成md题目列表"""
        print("""
|  题号  |  题解  |  标签  |  难度  | 备注 |
| --- | --- | --- | --- | --- |""")
        for item in sorted(self.md_table_cn, key=lambda x: x[0]):
            print("|  {}  |  {}  |  {}  |  {}  |  {}  |".format(item[0], item[1], item[2], item[3], item[4]))

        print('-------------------------')

        print("""
|  #  |  Solution  |  Tags  |  Difficulty  |  Remark |
| --- | --- | --- | --- | --- |""")
        for item in sorted(self.md_table_en, key=lambda x: x[0]):
            print("|  {}  |  {}  |  {}  |  {}  |  {}  |".format(item[0], item[1], item[2], item[3], item[4]))

        print('-------------------------')

    def generate_question_readme(self):
        with open('./README_TEMPLATE.md', 'r', encoding='utf-8') as f:
            readme_cn = f.read()
        with open('./README_TEMPLATE_EN.md', 'r', encoding='utf-8') as f:
            readme_en = f.read()

        with open('./result.json', 'r', encoding='utf-8') as f:
            result = f.read()
            result = json.loads(result)
            for item in result:
                no = int(item['frontend_question_id']) // 100

                path = f'./{self.number_mapper.get(str(no))}/{item["frontend_question_id"]}.{item["title_en"]}'
                path = path.replace(":", " ")

                if os.path.isdir(path):
                    continue
                os.makedirs(path)

                # generate readme cn
                with open(f'{path}/README.md', 'w', encoding='utf-8') as f1:
                    f1.write(readme_cn.format(int(item['frontend_question_id']),
                                              item["title_cn"],
                                              item['url_cn'],
                                              item['relative_path_en'],
                                              item['content_cn']))

                # generate readme en
                with open(f'{path}/README_EN.md', 'w', encoding='utf-8') as f2:
                    f2.write(readme_en.format(int(item['frontend_question_id']),
                                              item["title_en"],
                                              item['url_en'],
                                              item['relative_path_cn'],
                                              item['content_en']))

    @staticmethod
    def generate_summary():
        for file in os.listdir("./"):
            if os.path.isdir("./" + file) and file != '__pycache__':
                print("\n- " + file + "\n")
                for sub in os.listdir("./" + file):
                    enc = quote(sub)
                    print(f'\t- [{sub}](/solution/{file}/{enc}/README.md)')


if __name__ == '__main__':
    spider = LCSpider()

    spider.get_all_questions()
    spider.save_result()

    spider.generate_md_table()
    spider.generate_question_readme()
    spider.generate_summary()
