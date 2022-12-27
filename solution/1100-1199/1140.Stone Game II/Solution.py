class Solution:
    def stoneGameII(self, piles: List[int]) -> int:
        @cache
        def dfs(i, m):
            if m * 2 >= n - i:
                return s[-1] - s[i]
            res = 0
            for x in range(1, m << 1 | 1):
                t = s[-1] - s[i] - dfs(i + x, max(m, x))
                res = max(res, t)
            return res

        s = list(accumulate(piles, initial=0))
        n = len(piles)
        return dfs(0, 1)
