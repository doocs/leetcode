import re
import sys
from functools import cache

import requests

url = 'https://leetcode.cn/graphql'


# 分页加载排名列表
@cache
def load_page(page):
    query = "{\n  localRankingV2(page:" + str(
        page) + ") {\nmyRank {\nattendedContestCount\ncurrentRatingRanking\ndataRegion\nisDeleted\n" \
                "user {\nrealName\nuserAvatar\nuserSlug\n__typename\n}\n__typename\n}\npage\ntotalUsers\nuserPerPage\n" \
                "rankingNodes {\nattendedContestCount\ncurrentRatingRanking\ndataRegion\nisDeleted\n" \
                "user {\nrealName\nuserAvatar\nuserSlug\n__typename\n}\n__typename\n}\n__typename\n  }\n}\n"
    retry = 0
    while retry < 3:
        resp = requests.post(url=url, json={'query': query})
        if resp.status_code == 200:
            nodes = resp.json()['data']['localRankingV2']['rankingNodes']
            return [(int(nd['currentRatingRanking']), nd['user']['userSlug']) for nd in nodes]
        else:
            retry += 1
    return None


# 根据用户名获取其个人主页显示的真实分数，因为四舍五入会导致一部分 1599.xxx 的用户也显示为 1600 分
@cache
def get_user_rank(uid):
    operation_name = "userContest"
    query = "query userContest($userSlug: String!){\n userContestRanking(userSlug: $userSlug){" \
            "\ncurrentRatingRanking\nratingHistory\n}\n}\n "
    variables = {'userSlug': uid}
    retry = 0
    while retry < 3:
        resp = requests.post(url=url, json={
            'operationName': operation_name,
            'query': query,
            'variables': variables
        })
        if resp.status_code == 200:
            ranking = resp.json()['data']['userContestRanking']
            score = None
            if ranking and 'ratingHistory' in ranking:
                s = ranking['ratingHistory']
                mth = re.search(r'(\d+(?:\.\d+)?)(?:, null)*]$', s)
                if mth:
                    score = mth.group(1)
            return (ranking['currentRatingRanking'], float(score)) if score else (None, None)
        else:
            retry += 1
    return None, None


# 使用二分的方式获取1600分以上的人数，并使用 get_user_rank 方法校准
def get_1600_count() -> int:
    l, r = 1, 1000
    while l < r:
        mid = (l + r + 1) >> 1
        page = load_page(mid)
        print(f'第 {mid} 页：', page)
        if not page:
            return 0
        _, score = get_user_rank(page[0][1])
        if score < 1600:
            r = mid - 1
        else:
            l = mid
    page = load_page(l)
    print('校准中...')
    l, r = 0, len(page)
    while l < r:
        mid = (l + r + 1) >> 1
        _, score = get_user_rank(page[mid][1])
        if score < 1600:
            r = mid - 1
        else:
            l = mid

    return get_user_rank(page[l][1])[0]


# 获取指定排名的用户
@cache
def get_user(rank):
    if rank <= 0:
        raise Exception('无效的排名')
    p = (rank - 1) // 25 + 1
    off = (rank - 1) % 25
    page = load_page(p)
    _, score = get_user_rank(page[off][1])
    return score, page[off][1]


total = get_1600_count()
if not total:
    print('网络故障')
    sys.exit()
print(f'1600 分以上共计 {total} 人')

guardian = int(total * 0.05)
knight = int(total * 0.25)
g_first, g_last = get_user(1), get_user(guardian)
print(f'Guardian(top 5%): 共 {guardian} 名，守门员 {g_last[0]} 分（uid: {g_last[1]}），最高 {g_first[0]} 分（uid: {g_first[1]}）')
k_first, k_last = get_user(guardian + 1), get_user(knight)
print(f'Knight(top 25%): 共 {knight} 名，守门员 {k_last[0]} 分（uid: {k_last[1]}），最高 {k_first[0]} 分（uid: {k_first[1]}）')
