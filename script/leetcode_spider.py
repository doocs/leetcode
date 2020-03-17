import json
from urllib.parse import quote

from config import async_headers, cn_graphql_url, Req, all_problems_url, difficulties, lcof_problems_url, \
    lcci_problems_url, problems_url
from fetch import fetch


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
    '15': '1500-1599',

}


def get_all_questions():
    """获取所有题目"""
    cn_res = get_cn_questions()
    resp = fetch(url=all_problems_url, headers=async_headers)
    if resp is None:
        return
    res = resp.json()
    questions = res['stat_status_pairs']

    for question in questions:
        int_id = question['stat']['question_id']
        qid = str(question['stat']['question_id']).zfill(4)
        title = question['stat']['question__title']
        link = problems_url + question['stat']['question__title_slug']
        pre = no_dict[str(int(int_id) // 100)]
        git_link = '/solution/{}/{}/README.md'.format(pre, qid + '.' + quote(title))
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


if __name__ == '__main__':
    generate_md_table_for_questions(get_all_questions())
    # generate_md_table_for_questions(get_lcof_questions())
    # generate_md_table_for_questions(get_lcci_questions())
