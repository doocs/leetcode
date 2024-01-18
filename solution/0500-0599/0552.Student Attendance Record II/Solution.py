class Solution:
    def checkRecord(self, n: int) -> int:
        @cache
        def dfs(i, j, k):
            if i >= n:
                return 1
            ans = 0
            if j == 0:
                ans += dfs(i + 1, j + 1, 0)
            if k < 2:
                ans += dfs(i + 1, j, k + 1)
            ans += dfs(i + 1, j, 0)
            return ans % mod

        mod = 10**9 + 7
        ans = dfs(0, 0, 0)
        dfs.cache_clear()
        return ans
