class Solution:
    def findWinners(self, matches: List[List[int]]) -> List[List[int]]:
        cnt = Counter()
        for a, b in matches:
            if a not in cnt:
                cnt[a] = 0
            cnt[b] += 1
        ans = [[], []]
        for u, v in cnt.items():
            if v < 2:
                ans[v].append(u)
        ans[0].sort()
        ans[1].sort()
        return ans
