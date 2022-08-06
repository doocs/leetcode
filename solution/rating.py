import sys

import requests
import urllib3

urllib3.disable_warnings()

url = 'https://leetcode.cn/graphql'
global_url = 'https://leetcode.com/graphql'


class LocalRanking:
    """国内竞赛排名"""

    def __init__(self):
        self.url = 'https://leetcode.cn/graphql'
        self.rank_url = 'https://leetcode.cn/graphql/noj-go/'

    def load_page(self, page):
        """分页加载排名列表"""
        query = (
            "{\n  localRankingV2(page:"
            + str(page)
            + ") {\nmyRank {\nattendedContestCount\ncurrentRatingRanking\ndataRegion\nisDeleted\n"
            "user {\nrealName\nuserAvatar\nuserSlug\n__typename\n}"
            "\n__typename\n}\npage\ntotalUsers\nuserPerPage\n"
            "rankingNodes {\nattendedContestCount\ncurrentRatingRanking\ndataRegion\nisDeleted\n"
            "user {\nrealName\nuserAvatar\nuserSlug\n__typename\n}\n__typename\n}\n__typename\n  }\n}\n"
        )
        retry = 0
        while retry < 3:
            resp = requests.post(url=self.url, json={'query': query}, verify=False)
            if resp.status_code == 200:
                nodes = resp.json()['data']['localRankingV2']['rankingNodes']
                return [
                    (int(nd['currentRatingRanking']), nd['user']['userSlug'])
                    for nd in nodes
                ]
            else:
                retry += 1
        return None

    def get_user_rank(self, uid):
        """根据用户名获取其个人主页显示的真实分数，因为四舍五入会导致一部分 1599.xxx 的用户也显示为 1600 分"""
        query = (
            "\nquery userContestRankingInfo($userSlug:String!){\nuserContestRanking(userSlug:$userSlug){\n"
            "attendedContestsCount\nrating\nglobalRanking\nlocalRanking\nglobalTotalParticipants\n"
            "localTotalParticipants\ntopPercentage\n}\nuserContestRankingHistory(userSlug:$userSlug){\n"
            "attended\ntotalProblems\ntrendingDirection\nfinishTimeInSeconds\nrating\nscore\nranking\ncontest{\n"
            "title\ntitleCn\nstartTime\n}\n}\n}\n"
        )
        variables = {'userSlug': uid}
        retry = 0
        while retry < 3:
            resp = requests.post(
                url=self.rank_url,
                json={'query': query, 'variables': variables},
                verify=False,
            )
            if resp.status_code == 200:
                ranking = resp.json()['data']['userContestRanking']
                if ranking and 'localRanking' in ranking and 'rating' in ranking:
                    return int(ranking['localRanking']), float(ranking['rating'])
                return None, None
            else:
                retry += 1
        return None, None

    def get_1600_count(self):
        """使用二分的方式获取1600分以上的人数，并使用 get_user_rank 方法校准"""
        l, r = 1, 1000
        while l < r:
            mid = (l + r + 1) >> 1
            page = self.load_page(mid)
            print(f'第 {mid} 页：', page)
            if not page:
                return 0
            x, score = self.get_user_rank(page[0][1])
            if score < 1600:
                r = mid - 1
            else:
                l = mid
        page = self.load_page(l)
        print('校准中...')
        l, r = 0, len(page)
        while l < r:
            mid = (l + r + 1) >> 1
            _, score = self.get_user_rank(page[mid][1])
            if score < 1600:
                r = mid - 1
            else:
                l = mid

        return self.get_user_rank(page[l][1])[0]

    def get_user(self, rank):
        """获取指定排名的用户"""
        if rank <= 0:
            raise Exception('无效的排名')
        p = (rank - 1) // 25 + 1
        off = (rank - 1) % 25
        page = self.load_page(p)
        _, score = self.get_user_rank(page[off][1])
        return score, page[off][1]

    def fetch_ranking_data(self):
        """获取排名数据"""
        total = self.get_1600_count()
        if not total:
            print('网络故障')
            sys.exit()
        print(f'1600 分以上共计 {total} 人')

        guardian = int(total * 0.05)
        knight = int(total * 0.25)
        g_first, g_last = self.get_user(1), self.get_user(guardian)
        print(
            f'Guardian(top 5%): 共 {guardian} 名，守门员 {g_last[0]} 分（uid: {g_last[1]}），最高 {g_first[0]} 分（uid: {g_first[1]}）'
        )
        k_first, k_last = self.get_user(guardian + 1), self.get_user(knight)
        print(
            f'Knight(top 25%): 共 {knight} 名，守门员 {k_last[0]} 分（uid: {k_last[1]}），最高 {k_first[0]} 分（uid: {k_first[1]}）'
        )


class GlobalRanking:
    """全球竞赛排名"""

    def __init__(self):
        self.url = 'https://leetcode.com/graphql'
        self.lk = LocalRanking()

    def load_page(self, page):
        """分页加载排名列表"""
        query = (
            "{\nglobalRanking(page:"
            + str(page)
            + "){\ntotalUsers\nuserPerPage\nmyRank{\nranking\ncurrentGlobalRanking\ncurrentRating\ndataRegion\n"
            "user{\nnameColor\nactiveBadge{\ndisplayName\nicon\n__typename\n}\n__typename\n}\n__typename\n}\n"
            "rankingNodes{\nranking\ncurrentRating\ncurrentGlobalRanking\ndataRegion\nuser{\n"
            "username\nnameColor\n"
            "activeBadge{\ndisplayName\nicon\n__typename\n}\nprofile{\nuserAvatar\ncountryCode\ncountryName\n"
            "realName\n__typename\n}\n__typename\n}\n__typename\n}\n__typename\n}\n}\n"
        )
        retry = 0
        while retry < 3:
            resp = requests.post(url=self.url, json={'query': query}, verify=False)
            if resp.status_code == 200:
                nodes = resp.json()['data']['globalRanking']['rankingNodes']
                return [
                    (int(nd['currentGlobalRanking']), nd['user']['username'])
                    for nd in nodes
                ]
            else:
                retry += 1
        return None

    def get_user_rank(self, uid):
        """根据用户名获取其个人主页显示的真实分数，因为四舍五入会导致一部分 1599.xxx 的用户也显示为 1600 分"""
        query = (
            "\nquery userContestRankingInfo($username:String!){\nuserContestRanking(username:$username){\n"
            "attendedContestsCount\nrating\nglobalRanking\ntotalParticipants\ntopPercentage\nbadge{\n"
            "name\n}\n}\nuserContestRankingHistory(username:$username){\nattended\ntrendDirection\n"
            "problemsSolved\ntotalProblems\nfinishTimeInSeconds\nrating\nranking\ncontest{\n"
            "title\nstartTime\n}\n}\n}\n "
        )
        variables = {'username': uid}
        retry = 0
        while retry < 3:
            resp = requests.post(
                url=self.url,
                json={'query': query, 'variables': variables},
                verify=False,
            )
            if resp.status_code == 200:
                ranking = resp.json()['data']['userContestRanking']
                if ranking and 'globalRanking' in ranking and 'rating' in ranking:
                    score = float(ranking['rating'])
                    return int(ranking['globalRanking']), score
                return None, None
            else:
                retry += 1
        return None, None

    def get_1600_count(self):
        """使用二分的方式获取1600分以上的人数，并使用 get_user_rank 方法校准"""
        l, r = 1, 3000
        while l < r:
            mid = (l + r + 1) >> 1
            page = self.load_page(mid)
            print(f'第 {mid} 页：', page)
            if not page:
                return 0
            x, score = self.get_user_rank(page[0][1])
            if score is None:
                x, score = self.lk.get_user_rank(page[0][1])
            if score < 1600:
                r = mid - 1
            else:
                l = mid
        page = self.load_page(l)
        print('校准中...')
        l, r = 0, len(page)
        while l < r:
            mid = (l + r + 1) >> 1
            _, score = self.get_user_rank(page[mid][1])
            if score is None:
                x, score = self.lk.get_user_rank(page[mid][1])
            if score < 1600:
                r = mid - 1
            else:
                l = mid

        return self.get_user_rank(page[l][1])[0] or self.lk.get_user_rank(page[l][1])[0]

    def get_user(self, rank):
        """获取指定排名的用户"""
        if rank <= 0:
            raise Exception('无效的排名')
        p = (rank - 1) // 25 + 1
        off = (rank - 1) % 25
        page = self.load_page(p)
        _, score = self.get_user_rank(page[off][1])
        if score is None:
            _, score = self.lk.get_user_rank(page[off][1])
        return score, page[off][1]

    def fetch_ranking_data(self):
        """获取排名数据"""
        total = self.get_1600_count()
        if not total:
            print('网络故障')
            sys.exit()
        print(f'1600 分以上共计 {total} 人')

        guardian = int(total * 0.05)
        knight = int(total * 0.25)
        g_first, g_last = self.get_user(1), self.get_user(guardian)
        print(
            f'Guardian(top 5%): 共 {guardian} 名，守门员 {g_last[0]} 分（uid: {g_last[1]}），最高 {g_first[0]} 分（uid: {g_first[1]}）'
        )
        k_first, k_last = self.get_user(guardian + 1), self.get_user(knight)
        print(
            f'Knight(top 25%): 共 {knight} 名，守门员 {k_last[0]} 分（uid: {k_last[1]}），最高 {k_first[0]} 分（uid: {k_first[1]}）'
        )


local = LocalRanking()
local.fetch_ranking_data()

gk = GlobalRanking()
gk.fetch_ranking_data()
