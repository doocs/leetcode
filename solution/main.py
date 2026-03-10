import platform
import subprocess
import time
from datetime import timezone, timedelta, datetime

import requests
import urllib3

from util import *

urllib3.disable_warnings()

user_agent = (
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
    "Chrome/77.0.3865.120 Safari/537.36"
)
sub_folders = [
    str(i * 100).zfill(4) + "-" + str(i * 100 + 99).zfill(4) for i in range(1000)
]
cn_graph_url = "https://leetcode.cn/graphql"
difficulty = dict(Easy="简单", Medium="中等", Hard="困难")
weekly_range = range(83, 500)
biweekly_range = range(1, 300)
WEEKLY_URL = "https://leetcode.cn/contest/api/info/weekly-contest-{}/"
BIWEEKLY_URL = "https://leetcode.cn/contest/api/info/biweekly-contest-{}/"
WEEKLY_SLUG = "weekly-contest-{}"
BIWEEKLY_SLUG = "biweekly-contest-{}"


class Spider:
    def __init__(self, cookie1: str, cookie2: str):
        self.cookie_cn = cookie1
        self.cookie_en = cookie2

    def get_all_questions(self, retry: int = 3) -> List:
        """获取所有题目"""
        headers = {
            "accept": "application/json, text/javascript, */*; q=0.01",
            "content-type": "application/json",
            "user-agent": user_agent,
            "x-requested-with": "XMLHttpRequest",
            "cookie": self.cookie_en,
        }
        try:
            resp = requests.get(
                url="https://leetcode.com/api/problems/all/",
                headers=headers,
                allow_redirects=False,
                timeout=10,
                verify=False,
            )
            return resp.json()["stat_status_pairs"]
        except Exception as e:
            print("get_all_questions", e)
            time.sleep(2)
            return self.get_all_questions(retry - 1) if retry > 0 else []

    def get_all_questions_v2(self, retry: int = 3, limit: int = 100) -> List:
        headers = {
            "Cookie": self.cookie_en,
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0",
            "Content-Type": "application/json",
        }
        page_size = max(1, min(limit, 100))
        skip = 0
        total = None
        questions = []

        while total is None or skip < total:
            form = {
                "query": "\n    query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {\n  problemsetQuestionList: questionList(\n    categorySlug: $categorySlug\n    limit: $limit\n    skip: $skip\n    filters: $filters\n  ) {\n    total: totalNum\n    questions: data {\n      acRate\n      difficulty\n      freqBar\n      frontendQuestionId: questionFrontendId\n      isFavor\n      paidOnly: isPaidOnly\n      status\n      title\n      titleSlug\n      topicTags {\n        name\n        id\n        slug\n      }\n      hasSolution\n      hasVideoSolution\n    }\n  }\n}\n    ",
                "variables": {
                    "categorySlug": "all-code-essentials",
                    "skip": skip,
                    "limit": page_size,
                    "filters": {"orderBy": "FRONTEND_ID", "sortOrder": "DESCENDING"},
                },
                "operationName": "problemsetQuestionList",
            }

            ok = False
            for _ in range(max(0, retry) + 1):
                try:
                    resp = requests.post(
                        "https://leetcode.com/graphql",
                        headers=headers,
                        data=json.dumps(form),
                        timeout=20,
                    )
                    page = resp.json()["data"]["problemsetQuestionList"]
                    page_questions = page.get("questions") or []
                    total = page.get("total") if total is None else total
                    questions.extend(page_questions)
                    skip += len(page_questions)
                    ok = True
                    break
                except Exception as e:
                    print("get_all_questions_v2", e)
                    time.sleep(2)

            if not ok:
                break

            if not page_questions:
                break

        return questions

    def get_question_detail_en(self, question_title_slug: str, retry: int = 3) -> dict:
        headers = {
            "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;"
            "q=0.8,application/signed-exchange;v=b3;q=0.7",
            "user-agent": user_agent,
            "cookie": self.cookie_en,
        }
        question_url = "https://leetcode.com/problems" + question_title_slug
        en_graph_url = "https://leetcode.com/graphql"
        form = {
            "operationName": "questionData",
            "variables": {"titleSlug": question_title_slug},
            "query": "query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    "
            "questionId\n    questionFrontendId\n    categoryTitle\n    boundTopicId\n    title\n    "
            "titleSlug\n    content\n    translatedTitle\n    translatedContent\n    isPaidOnly\n    "
            "difficulty\n    likes\n    dislikes\n    isLiked\n    similarQuestions\n    "
            "contributors {\n      username\n      profileUrl\n      avatarUrl\n      __typename\n    "
            "}\n    langToValidPlayground\n    topicTags {\n      name\n      slug\n      "
            "translatedName\n      __typename\n    }\n    companyTagStats\n    codeSnippets {\n      "
            "lang\n      langSlug\n      code\n      __typename\n    }\n    stats\n    hints\n    "
            "solution {\n      id\n      canSeeDetail\n      __typename\n    }\n    status\n    "
            "sampleTestCase\n    metaData\n    judgerAvailable\n    judgeType\n    mysqlSchemas\n    "
            "exampleTestcases\n    __typename\n  }\n}\n",
        }
        for _ in range(max(0, retry) + 1):
            try:
                requests.get(question_url, headers=headers, timeout=10, verify=False)
                headers = {
                    "User-Agent": user_agent,
                    "Connection": "keep-alive",
                    "Content-Type": "application/json",
                    "Referer": "https://leetcode.com/problems/" + slug,
                    "cookie": self.cookie_en,
                }
                resp = requests.post(
                    en_graph_url,
                    headers=headers,
                    data=json.dumps(form),
                    timeout=10,
                    verify=False,
                )
                res = resp.json()
                return res["data"]["question"] or {}
            except Exception as e:
                print("get_question_detail_en", e)
                if "is not defined" in str(e):
                    return {}
                time.sleep(2)
        return {}

    def get_question_detail(self, question_title_slug: str, retry: int = 3) -> dict:
        """获取题目详情"""
        form1 = {
            "operationName": "globalData",
            "query": "query globalData {\n  feature {\n    questionTranslation\n    subscription\n    signUp\n    "
            "discuss\n    mockInterview\n    contest\n    store\n    book\n    chinaProblemDiscuss\n    "
            "socialProviders\n    studentFooter\n    cnJobs\n    enableLsp\n    enableWs\n    "
            "enableDebugger\n    enableDebuggerAdmin\n    enableDarkMode\n    tasks\n    "
            "leetbook\n    __typename\n  }\n  userStatus {\n    isSignedIn\n    isAdmin\n    "
            "isStaff\n    isSuperuser\n    isTranslator\n    isPremium\n    isVerified\n    "
            "isPhoneVerified\n    isWechatVerified\n    checkedInToday\n    username\n    "
            "realName\n    userSlug\n    groups\n    avatar\n    optedIn\n    "
            "requestRegion\n    region\n    activeSessionId\n    permissions\n    notificationStatus {\n      "
            "lastModified\n      numUnread\n      __typename\n    }\n    completedFeatureGuides\n    "
            "useTranslation\n    accountStatus {\n      isFrozen\n      inactiveAfter\n      __typename\n    "
            "}\n    __typename\n  }\n  siteRegion\n  chinaHost\n  websocketUrl\n  userBannedInfo {\n    "
            "bannedData {\n      endAt\n      bannedType\n      __typename\n    }\n    __typename\n  }\n}\n",
            "variables": {},
        }
        headers = {
            "User-Agent": user_agent,
            "Connection": "keep-alive",
            "Content-Type": "application/json",
            "Referer": "https://leetcode.cn/problems/" + question_title_slug,
            "cookie": self.cookie_cn,
        }

        form2 = {
            "operationName": "questionData",
            "variables": {"titleSlug": question_title_slug},
            "query": "query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    "
            "questionId\n    questionFrontendId\n    categoryTitle\n    boundTopicId\n    title\n    "
            "titleSlug\n    content\n    translatedTitle\n    translatedContent\n    isPaidOnly\n    "
            "difficulty\n    likes\n    dislikes\n    isLiked\n    similarQuestions\n    "
            "contributors {\n      username\n      profileUrl\n      avatarUrl\n      __typename\n    "
            "}\n    langToValidPlayground\n    topicTags {\n      name\n      slug\n      "
            "translatedName\n      __typename\n    }\n    companyTagStats\n    codeSnippets {\n      "
            "lang\n      langSlug\n      code\n      __typename\n    }\n    stats\n    hints\n    "
            "solution {\n      id\n      canSeeDetail\n      __typename\n    }\n    status\n    "
            "sampleTestCase\n    metaData\n    judgerAvailable\n    judgeType\n    mysqlSchemas\n    "
            "enableRunCode\n    envInfo\n    book {\n      id\n      bookName\n      pressName\n      "
            "source\n      shortDescription\n      fullDescription\n      bookImgUrl\n      "
            "pressImgUrl\n      productUrl\n      __typename\n    }\n    isSubscribed\n    "
            "isDailyQuestion\n    dailyRecordStatus\n    editorType\n    ugcQuestionId\n    style\n    "
            "exampleTestcases\n    __typename\n  }\n}\n",
        }
        for _ in range(max(0, retry) + 1):
            try:
                requests.post(
                    url=cn_graph_url,
                    data=json.dumps(form1),
                    headers=headers,
                    timeout=10,
                    verify=False,
                )
                # get question detail
                resp = requests.post(
                    url=cn_graph_url,
                    data=json.dumps(form2).encode("utf-8"),
                    headers=headers,
                    timeout=10,
                    verify=False,
                )
                res = resp.json()
                return res["data"]["question"] or {}
            except Exception as e:
                print("get_question_detail", e)
                time.sleep(2)
        return {}

    @staticmethod
    def format_question_detail(question_detail: dict, qid: str = None) -> dict:
        question_title_slug = question_detail.get("titleSlug")
        url_cn = f"https://leetcode.cn/problems/{question_title_slug}"
        url_en = f"https://leetcode.com/problems/{question_title_slug}"
        frontend_question_id = qid or str(question_detail["questionFrontendId"]).zfill(
            4
        )
        no = int(frontend_question_id) // 100
        question_title_en = question_detail["title"]
        question_title_en = re.sub(r'[\\/:*?"<>|]', "", question_title_en).strip()
        path_cn = f"/solution/{sub_folders[no]}/{frontend_question_id}.{quote(question_title_en)}/README.md"
        path_en = f"/solution/{sub_folders[no]}/{frontend_question_id}.{quote(question_title_en)}/README_EN.md"

        print(f"{frontend_question_id}. {question_title_en}")
        topic_tags = question_detail.get("topicTags")
        for tag in topic_tags:
            if tag["name"] == "数据库":
                tag["name"] = "Database"

        item = {
            "sub_folder": sub_folders[no],
            "question_id": str(question_detail["questionId"]).zfill(4),
            "frontend_question_id": frontend_question_id,
            "paid_only": question_detail.get("isPaidOnly"),
            "paid_only_cn": question_detail.get("isPaidOnly"),
            # Shell Database Algorithms Concurrency
            "category": question_detail.get("categoryTitle"),
            "url_cn": url_cn,
            "url_en": url_en,
            "relative_path_cn": path_cn,
            "relative_path_en": path_en,
            "title_cn": question_detail.get("translatedTitle")
            or question_title_en
            or "",
            "title_en": question_title_en or "",
            "question_title_slug": question_title_slug,
            "content_en": question_detail.get("content"),
            "content_cn": question_detail.get("translatedContent")
            or question_detail.get("content")
            or "",
            "tags_en": [e["name"] for e in topic_tags if e["name"]] or [],
            "tags_cn": [e["translatedName"] for e in topic_tags if e["translatedName"]]
            or [],
            "difficulty_en": question_detail.get("difficulty"),
            "difficulty_cn": difficulty.get(question_detail.get("difficulty")),
            "code_snippets": question_detail.get("codeSnippets") or [],
        }

        col1_cn = frontend_question_id
        col2_cn = (
            f'[{item["title_cn"]}]({path_cn})'
            if item["title_cn"]
            else f'[{item["title_en"]}]({path_en})'
        )
        col3_cn = ",".join([f"`{tag}`" for tag in item["tags_cn"]])
        col3_cn = "" if (col3_cn == "None" or not col3_cn) else col3_cn
        col4_cn = item["difficulty_cn"]
        col5_cn = "🔒" if item["paid_only_cn"] else ""
        col1_en = frontend_question_id
        col2_en = f'[{item["title_en"]}]({path_en})'
        col3_en = ",".join([f"`{tag}`" for tag in item["tags_en"]])
        col3_en = "" if (col3_en == "None" or not col3_en) else col3_en
        col4_en = item["difficulty_en"]
        col5_en = "🔒" if item["paid_only"] else ""

        item["md_table_row_cn"] = [col1_cn, col2_cn, col3_cn, col4_cn, col5_cn]
        item["md_table_row_en"] = [col1_en, col2_en, col3_en, col4_en, col5_en]
        return item


class Contest:
    def __init__(self, contest_seq: int, contest_type: int = 1):
        double = contest_type % 2 == 0
        url_pattern = BIWEEKLY_URL if double else WEEKLY_URL
        slug_pattern = BIWEEKLY_SLUG if double else WEEKLY_SLUG
        self.contest_type = contest_type
        self.contest_url = url_pattern.format(contest_seq)
        self.contest_title_slug = slug_pattern.format(contest_seq)
        self.contest_title = (
            f"第 {contest_seq} 场双周赛" if double else f"第 {contest_seq} 场周赛"
        )
        self.contest_title_en = (
            f"Biweekly Contest {contest_seq}"
            if double
            else f"Weekly Contest {contest_seq}"
        )

    @staticmethod
    def format_time(timestamp: int) -> str:
        tz = timezone(timedelta(hours=+8))
        return datetime.fromtimestamp(timestamp, tz).strftime("%Y-%m-%d %H:%M")

    def get_data(self, retry: int = 3):
        try:
            print(self.contest_url)
            headers = {
                'User-Agent': user_agent,
                'Host': 'leetcode.cn',
                'content-type': 'application/json',
                'Accept': 'application/json, text/javascript, */*; q=0.01',
            }
            res = requests.get(
                self.contest_url, timeout=6, verify=False, headers=headers
            )
            res = res.json()
            if not res or "error" in res or not res["questions"]:
                return {}
            questions = res["questions"]
            question_slugs = [q["title_slug"] for q in questions]
            return {
                "contest_title": self.contest_title,
                "contest_title_en": self.contest_title_en,
                "contest_title_slug": res["contest"]["title_slug"],
                "contest_id": res["contest"]["id"],
                "contest_start_time": res["contest"]["origin_start_time"],
                "contest_duration": res["contest"]["duration"],
                "user_num": res["user_num"],
                "question_slugs": question_slugs,
            }
        except Exception as e:
            print(e)
            time.sleep(2)
            return self.get_data(retry - 1) if retry > 0 else {}

    @staticmethod
    def format(data: dict) -> List:
        if not data:
            return []
        title = data["contest_title"]
        title_en = data["contest_title_en"]
        start_time = data["contest_start_time"]
        duration = data["contest_duration"]
        cost_minutes = duration // 60
        user_num = data["user_num"]
        rows = [
            f"#### {title}({Contest.format_time(start_time)}, {cost_minutes} 分钟) 参赛人数 {user_num}\n"
        ]
        rows_en = [f"#### {title_en}\n"]
        for question in data["question_list"]:
            (
                frontend_question_id,
                title_cn,
                title_en,
                relative_path_cn,
                relative_path_en,
            ) = question
            rows.append(f"- [{frontend_question_id}. {title_cn}]({relative_path_cn})")
            rows_en.append(
                f"- [{frontend_question_id}. {title_en}]({relative_path_en})"
            )
        return [start_time, "\n".join(rows), "\n".join(rows_en)]


def get_contests(fetch_new=True) -> List:
    res = [] if fetch_new else load_contest_result()
    t = 0
    d = {x.get("contest_title_slug"): x for x in res}
    for r in (weekly_range, biweekly_range):
        t += 1
        cnt = 0
        for i in r:
            c = Contest(i, contest_type=t)
            if c.contest_title_slug in d:
                continue
            contest_data = c.get_data(retry=10)
            time.sleep(1)
            if not contest_data:
                cnt += 1
                if cnt > 2:
                    break
                continue
            print(contest_data)
            res.append(contest_data)
            d[c.contest_title_slug] = contest_data
    save_contest_result(res)
    return res


########################################################################################


def format_rust_files_linux():
    # The find command to locate and format all .rs files in Linux
    find_command = 'find . -name "*.rs" -exec rustfmt {} \\;'

    # Execute the command
    process = subprocess.Popen(
        find_command,
        shell=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
    )

    # Get the output and errors
    stdout, stderr = process.communicate()

    if process.returncode == 0:
        print("Rust files formatted successfully on Linux!")
        print(stdout)
    else:
        print("Error formatting Rust files on Linux:")
        print(stderr)


def format_rust_files_windows():
    # PowerShell command to format all .rs files recursively in Windows
    ps_command = (
        "Get-ChildItem -Recurse -Filter *.rs | ForEach-Object { rustfmt $_.FullName }"
    )

    # Execute the PowerShell command
    process = subprocess.Popen(
        ["powershell", "-Command", ps_command],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
    )

    # Get the output and errors
    stdout, stderr = process.communicate()

    if process.returncode == 0:
        print("Rust files formatted successfully on Windows!")
        print(stdout)
    else:
        print("Error formatting Rust files on Windows:")
        print(stderr)


def run():
    # 加载 cookies
    cookie_cn, cookie_en = load_cookies()
    spider = Spider(cookie_cn, cookie_en)

    # 是否刷新所有题目
    refresh_all = load_refresh_config()

    question_details = {}
    if not refresh_all:
        for item in load_result():
            slug = item.get("question_title_slug")
            if slug:
                question_details[slug] = item

    for q in spider.get_all_questions_v2(retry=6):
        try:
            slug = q["stat"]["question__title_slug"]
            qid = q["stat"]["frontend_question_id"]
        except:
            slug = q["titleSlug"]
            qid = int(q["frontendQuestionId"])
        if slug in question_details:
            continue
        detail = spider.get_question_detail(
            slug, retry=4
        ) or spider.get_question_detail_en(slug, retry=8)
        if not detail:
            continue
        time.sleep(1)
        question_details[slug] = Spider.format_question_detail(
            detail, str(qid).zfill(4)
        )

    # 周赛场次列表
    contest_list = get_contests(refresh_all)
    cls = []
    for contest in contest_list:
        contest_title = contest["contest_title"]
        contest_title_en = contest["contest_title_en"]
        contest_question_list = []
        for slug in contest["question_slugs"]:
            if slug in question_details:
                detail = question_details.get(slug)
                # 给题目详情添加周赛信息
                detail["md_table_row_cn"][4] = contest_title
                detail["md_table_row_en"][4] = contest_title_en

                # 给周赛信息添加题目详情
                add = [
                    detail["frontend_question_id"],
                    detail["title_cn"],
                    detail["title_en"],
                    detail["relative_path_cn"],
                    detail["relative_path_en"],
                ]
                contest_question_list.append(add)

        contest["question_list"] = contest_question_list
        cls.append(Contest.format(contest))

    # 保存题目到本地，生成题目列表以及题目详情文件
    ls = list(question_details.values())
    save_result(ls)
    ls = load_result()
    generate_readme(ls)
    generate_question_readme(ls)

    # 生成周赛题目列表
    generate_contest_readme(cls)

    # 生成分类题目列表
    generate_category_readme(ls, "Database")
    generate_category_readme(ls, "JavaScript")

    refresh(ls)
    # 格式化
    os.system('cd .. && npx prettier --write "**/*.{md,js,ts,php,sql}"')

    # 格式化 rust 代码
    # 判断当前是 windows 还是 linux
    if platform.system() == "Linux":
        format_rust_files_linux()
    elif platform.system() == "Windows":
        format_rust_files_windows()


if __name__ == "__main__":
    run()
