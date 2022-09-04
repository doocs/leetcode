class Solution:
    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        @cache
        def dfs(d, k):
            if k < 0 or abs(d) > k:
                return 0
            if k == 0:
                return d == 0
            res = dfs(d - 1, k - 1) + dfs(d + 1, k - 1)
            return res % (10**9 + 7)

        return dfs(abs(startPos - endPos), k)
