from string import Template

import requests
import urllib3

urllib3.disable_warnings()


class Ranking:
    def __init__(self, region='CN', retry=3):
        self.retry = retry
        self.region = region
        if region == 'CN':
            self.url = 'https://leetcode.cn/graphql'
            self.page_query = Template(
                "{\n  localRankingV2(page:$page) {\nmyRank {\nattendedContestCount\n"
                "currentRatingRanking\ndataRegion\nisDeleted\nuser {\nrealName\n"
                "userAvatar\nuserSlug\n__typename\n}\n__typename\n}\npage\ntotalUsers\n"
                "userPerPage\nrankingNodes {\nattendedContestCount\ncurrentRatingRanking\n"
                "dataRegion\nisDeleted\nuser {\nrealName\nuserAvatar\nuserSlug\n__typename\n}\n__"
                "typename\n}\n__typename\n  }\n}\n"
            )
        else:
            self.url = 'https://leetcode.com/graphql'
            self.page_query = Template(
                "{\nglobalRanking(page:$page){\ntotalUsers\nuserPerPage\nmyRank{\nranking\n"
                "currentGlobalRanking\ncurrentRating\ndataRegion\nuser{\nnameColor\nactiveBadge{\n"
                "displayName\nicon\n__typename\n}\n__typename\n}\n__typename\n}\nrankingNodes{\n"
                "ranking\ncurrentRating\ncurrentGlobalRanking\ndataRegion\nuser{\nusername\nnameColor\n"
                "activeBadge{\ndisplayName\nicon\n__typename\n}\nprofile{\nuserAvatar\ncountryCode\n"
                "countryName\nrealName\n__typename\n}\n__typename\n}\n__typename\n}\n__typename\n}\n}\n"
            )

    def load_page(self, page):
        query = self.page_query.substitute(page=page)
        for _ in range(self.retry):
            resp = requests.post(url=self.url, json={'query': query}, verify=False)
            if resp.status_code != 200:
                continue
            nodes = resp.json()['data'][
                'localRankingV2' if self.region == 'CN' else 'globalRanking'
            ]['rankingNodes']
            if self.region == 'CN':
                return [
                    (int(v['currentRatingRanking']), v['user']['userSlug'])
                    for v in nodes
                ]
            else:
                return [
                    (int(v['currentGlobalRanking']), v['user']['username'])
                    for v in nodes
                ]
        return []

    def _user_ranking(self, region, uid):
        if region == 'CN':
            key = 'userSlug'
            url = 'https://leetcode.cn/graphql/noj-go/'
            query = (
                "\nquery userContestRankingInfo($userSlug:String!){\nuserContestRanking"
                "(userSlug:$userSlug){\nattendedContestsCount\nrating\nglobalRanking\nlocalRanking\n"
                "globalTotalParticipants\nlocalTotalParticipants\ntopPercentage\n}\n"
                "userContestRankingHistory(userSlug:$userSlug){\nattended\ntotalProblems\n"
                "trendingDirection\nfinishTimeInSeconds\nrating\nscore\nranking\ncontest{\n"
                "title\ntitleCn\nstartTime\n}\n}\n}\n"
            )
        else:
            key = 'username'
            url = 'https://leetcode.com/graphql'
            query = (
                "\nquery userContestRankingInfo($username:String!){\nuserContestRanking"
                "(username:$username){\nattendedContestsCount\nrating\nglobalRanking\n"
                "totalParticipants\ntopPercentage\nbadge{\nname\n}\n}\nuserContestRankingHistory"
                "(username:$username){\nattended\ntrendDirection\nproblemsSolved\n"
                "totalProblems\nfinishTimeInSeconds\nrating\nranking\ncontest{\n"
                "title\nstartTime\n}\n}\n}\n "
            )

        variables = {key: uid}
        for _ in range(self.retry):
            resp = requests.post(
                url=url, json={'query': query, 'variables': variables}, verify=False
            )
            if resp.status_code != 200:
                continue
            res = resp.json()
            if 'errors' in res:
                break
            ranking = res['data']['userContestRanking']
            if ranking and 'rating' in ranking:
                score = float(ranking['rating'])
                if 'localRanking' in ranking:
                    return int(ranking['localRanking']), score
                if 'globalRanking' in ranking:
                    return int(ranking['globalRanking']), score
            return None, None

    def get_user_ranking(self, uid):
        for region in ['CN', 'US']:
            # 美国站会有国服用户，这里最多需要查询两次
            ranking, score = self._user_ranking(region, uid)
            if score is not None:
                return ranking, score
        return None

    def get_1600_count(self):
        left, right = 1, 1000 if self.region == 'CN' else 3000
        while left < right:
            mid = (left + right + 1) >> 1
            page = self.load_page(mid)
            print(f'第 {mid} 页：', page)
            if not page:
                return 0
            ranking, score = self.get_user_ranking(page[0][1])
            if score >= 1600:
                left = mid
            else:
                right = mid - 1
        page = self.load_page(left)
        print('校准中...')
        left, right = 0, len(page)
        while left < right:
            mid = (left + right + 1) >> 1
            ranking, score = self.get_user_ranking(page[mid][1])
            if score >= 1600:
                left = mid
            else:
                right = mid - 1
        return self.get_user_ranking(page[left][1])[0]

    def get_user(self, rank):
        p = (rank - 1) // 25 + 1
        offset = (rank - 1) % 25
        page = self.load_page(p)
        _, score = self.get_user_ranking(page[offset][1])
        return score, page[offset][1]

    def fetch_ranking_data(self):
        total = self.get_1600_count()
        if not total:
            return
        print(f'[{self.region}] 1600 分以上共计 {total} 人')

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


# 国服竞赛排名
lk = Ranking(region='CN')
lk.fetch_ranking_data()

print('\n------------------------------\n')

# 全球竞赛排名
gk = Ranking(region='US')
gk.fetch_ranking_data()
