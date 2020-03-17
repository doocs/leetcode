import json
import os
from urllib.parse import quote

from config import async_headers, cn_graphql_url, Req, all_problems_url, difficulties, lcof_problems_url, \
    lcci_problems_url, problems_url, en_graphql_url
from fetch import fetch
from readme_template import template


def get_cn_questions() -> dict:
    """获取所有题目的ID和中文标题"""
    form_data = {
        'operationName': 'getQuestionTranslation',
        'variables': {

        },
        'query': 'query getQuestionTranslation($lang: String) {\n  translations: '
                 'allAppliedQuestionTranslations(lang: $lang) {\n    title\n    questionId\n    __typename\n  }\n}\n'
    }

    resp = fetch(url=cn_graphql_url, method=Req.POST, headers=async_headers, data=json.dumps(form_data))
    if resp is None:
        return dict()
    res = resp.json()
    questions = res['data']['translations']
    final_res = dict()
    for q in questions:
        qid = str(q['questionId']).zfill(4)
        final_res[qid] = q['title']
    return final_res


def get_all_questions():
    """获取所有题目"""
    cn_res = get_cn_questions()
    resp = fetch(url=all_problems_url, headers=async_headers)
    if resp is None:
        return
    res = resp.json()
    questions = res['stat_status_pairs']

    for question in questions:
        qid = str(question['stat']['question_id']).zfill(4)
        title = question['stat']['question__title']
        link = problems_url + question['stat']['question__title_slug']
        git_link = '/solution/{}/README.md'.format(qid + '.' + quote(title))
        cn_title = cn_res.get(qid) or title
        col1 = '[{}]({})'.format(qid, link)
        col2 = '[{}]({})'.format(cn_title, git_link)
        col3 = difficulties.get(str(question['difficulty']['level']))
        yield [col1, col2, col3]


def get_lcof_questions():
    """获取剑指Offer题目"""
    resp = fetch(url=lcof_problems_url, headers=async_headers)
    if resp is None:
        return None
    res = resp.json()
    questions = res['stat_status_pairs']
    for question in questions:
        fe_qid = question['stat']['frontend_question_id']
        qno = fe_qid.replace('面试题', '').strip()
        title = question['stat']['question__title'].replace(' LCOF', '').strip()
        link = problems_url + question['stat']['question__title_slug']
        git_link = '/lcof/{}/README.md'.format(quote(fe_qid + '. ' + title))
        col1 = '[{}]({})'.format(qno, link)
        col2 = '[{}]({})'.format(title, git_link)
        col3 = difficulties.get(str(question['difficulty']['level']))
        yield [col1, col2, col3]


def get_lcci_questions():
    """获取程序员面试金典题目"""
    cn_res = get_cn_questions()
    resp = fetch(url=lcci_problems_url, headers=async_headers)
    if resp is None:
        return None
    res = resp.json()
    questions = res['stat_status_pairs']
    for question in questions:
        qid = question['stat']['question_id']
        fe_qid = question['stat']['frontend_question_id']
        qno = fe_qid.replace('面试题', '').strip()
        cn_title = cn_res.get(str(qid))
        link = problems_url + question['stat']['question__title_slug']
        git_link = '/lcci/{}/README.md'.format(quote(fe_qid + '. ' + cn_title))
        col1 = '[{}]({})'.format(qno, link)
        col2 = '[{}]({})'.format(cn_title, git_link)
        col3 = difficulties.get(str(question['difficulty']['level']))
        yield [col1, col2, col3]


def generate_md_table_for_questions(res):
    """生成markdown形式的表格"""
    print("""
|  题号  |  题解  |  难度  |
| --- | --- | --- |""")
    for item in sorted(res, key=lambda x: x[0]):
        print("|  {}  |  {}  |  {}  |".format(item[0], item[1], item[2]))

    print('-------------------------')


no_dict = {
    '0': '0000-0099',
    '1': '0100-0199',
    '2': '0200-0299',
    '3': '0300-0399',
    '4': '0400-0499',
    '5': '0500-0599',
    '6': '0600-0699',
    '7': '0700-0799',
    '8': '0800-0899',
    '9': '0900-0999',
    '10': '1000-1099',
    '11': '1100-1199',
    '12': '1200-1299',
    '13': '1300-1399',
    '14': '1400-1499',

}

def get_cn_questions():
    form_data = {
        'operationName': 'allQuestions',
        'query': 'query allQuestions {\n  allQuestionsBeta {\n    ...questionSummaryFields\n    __typename\n  }\n}\n\nfragment questionSummaryFields on QuestionNode {\n  title\n  titleSlug\n  translatedTitle\n  questionId\n  questionFrontendId\n  status\n  difficulty\n  isPaidOnly\n  categoryTitle\n  __typename\n}\n',
        'variables': {

        }
    }
    detail_data = {
        'operationName': 'questionData',
        'query': 'query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    questionId\n    questionFrontendId\n    boundTopicId\n    title\n    titleSlug\n    content\n    translatedTitle\n    translatedContent\n    isPaidOnly\n    difficulty\n    likes\n    dislikes\n    isLiked\n    similarQuestions\n    contributors {\n      username\n      profileUrl\n      avatarUrl\n      __typename\n    }\n    langToValidPlayground\n    topicTags {\n      name\n      slug\n      translatedName\n      __typename\n    }\n    companyTagStats\n    codeSnippets {\n      lang\n      langSlug\n      code\n      __typename\n    }\n    stats\n    hints\n    solution {\n      id\n      canSeeDetail\n      __typename\n    }\n    status\n    sampleTestCase\n    metaData\n    judgerAvailable\n    judgeType\n    mysqlSchemas\n    enableRunCode\n    envInfo\n    book {\n      id\n      bookName\n      pressName\n      source\n      shortDescription\n      fullDescription\n      bookImgUrl\n      pressImgUrl\n      productUrl\n      __typename\n    }\n    isSubscribed\n    isDailyQuestion\n    dailyRecordStatus\n    editorType\n    ugcQuestionId\n    __typename\n  }\n}\n',
        'variables': {
            'titleSlug': ''
        }
    }

    resp = fetch(url=cn_graphql_url, method=Req.POST, headers=async_headers, data=json.dumps(form_data))
    if resp is None:
        return
    print(resp.text)
    res = resp.json()
    questions = res['data']['allQuestionsBeta']
    final_res = dict()

    for q in questions:
        qfid = q['questionFrontendId']
        qfid_zero = str(q['questionFrontendId']).zfill(4)
        title = q['title'].strip()
        cn_title = q['translatedTitle'].strip()
        title_slug = q['titleSlug'].strip()
        difficulty = q['difficulty']
        pre = no_dict[str(int(qfid) // 100)]
        folder_name = '{}/{}.{}'.format(pre, qfid_zero, title).replace('?', '').replace(':', '')
        link = 'https://leetcode-cn.com/problems/{}'.format(title_slug)
        detail_data['variables']['titleSlug'] = title_slug
        print(detail_data)
        detail_resp = fetch(cn_graphql_url, method=Req.POST, headers=async_headers, data=json.dumps(detail_data))
        if detail_resp is None:
            continue
        detail_res = detail_resp.json()
        question_content = detail_res['data']['question']['translatedContent']

        file_dir = '{}'.format(folder_name)
        if not os.path.isdir(file_dir):
            os.makedirs(file_dir)
        print(file_dir)
        with open(file_dir + '/README.md', 'w', encoding='utf-8') as f:
            content = template.format(qfid + '. ' + cn_title, link, question_content).replace('\u200b', '').replace('\ufe48', '')
            f.flush()
            f.write(content)



def get_en_questions():
    form_data = {
        'operationName': 'allQuestionsRaw',
        'query': 'query allQuestionsRaw {\n  allQuestions: allQuestionsRaw {\n    title\n    titleSlug\n    translatedTitle\n    questionId\n    questionFrontendId\n    status\n    difficulty\n    isPaidOnly\n    categoryTitle\n    __typename\n  }\n}\n',
        'variables': {

        }
    }
    detail_data = {
        'operationName': 'questionData',
        'query': 'query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    questionId\n    questionFrontendId\n    boundTopicId\n    title\n    titleSlug\n    content\n    translatedTitle\n    translatedContent\n    isPaidOnly\n    difficulty\n    likes\n    dislikes\n    isLiked\n    similarQuestions\n    contributors {\n      username\n      profileUrl\n      avatarUrl\n      __typename\n    }\n    langToValidPlayground\n    topicTags {\n      name\n      slug\n      translatedName\n      __typename\n    }\n    companyTagStats\n    codeSnippets {\n      lang\n      langSlug\n      code\n      __typename\n    }\n    stats\n    hints\n    solution {\n      id\n      canSeeDetail\n      paidOnly\n      __typename\n    }\n    status\n    sampleTestCase\n    metaData\n    judgerAvailable\n    judgeType\n    mysqlSchemas\n    enableRunCode\n    enableTestMode\n    enableDebugger\n    envInfo\n    libraryUrl\n    adminUrl\n    __typename\n  }\n}\n',
        'variables': {
            'titleSlug': ''
        }
    }

    resp = fetch(url=en_graphql_url, method=Req.POST, headers=async_headers, data=json.dumps(form_data))
    if resp is None:
        return
    res = resp.json()
    questions = res['data']['allQuestions']
    final_res = dict()

    for q in questions:
        qfid = q['questionFrontendId']
        qfid_zero = str(q['questionFrontendId']).zfill(4)
        title = q['title'].strip()
        title_slug = q['titleSlug'].strip()
        difficulty = q['difficulty']
        pre = no_dict[str(int(qfid) // 100)]
        folder_name = '{}/{}.{}'.format(pre, qfid_zero, title).replace('?', '').replace(':', '')
        link = 'https://leetcode.com/problems/{}'.format(title_slug)
        detail_data['variables']['titleSlug'] = title_slug

        detail_resp = fetch(en_graphql_url, method=Req.POST, headers=async_headers, data=json.dumps(detail_data))
        if detail_resp is None:
            continue
        detail_res = detail_resp.json()
        question_content = detail_res['data']['question']['content']

        file_dir = '{}'.format(folder_name)
        if not os.path.isdir(file_dir):
            os.makedirs(file_dir)
        print(file_dir)
        with open(file_dir + '/README_EN.md', 'w', encoding='utf-8') as f:
            content = template.format(qfid + '. ' + title, link, question_content).replace('\u200b', '').replace('\ufe48', '')
            f.flush()
            f.write(content)


if __name__ == '__main__':
    # generate_md_table_for_questions(get_all_questions())
    # generate_md_table_for_questions(get_lcof_questions())
    # generate_md_table_for_questions(get_lcci_questions())
    get_en_questions()
