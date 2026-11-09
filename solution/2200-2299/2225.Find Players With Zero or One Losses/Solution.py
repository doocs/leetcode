class Solution:
    def findWinners(self, matches: list[list[int]]) -> list[list[int]]:
        cnt = Counter()
        for winner, loser in matches:
            if winner not in cnt:
                cnt[winner] = 0
            cnt[loser] += 1
        ans = [[], []]
        for x, v in sorted(cnt.items()):
            if v < 2:
                ans[v].append(x)
        return ans
