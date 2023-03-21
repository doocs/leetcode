class Solution:
    def numWays(self, steps: int, arrLen: int) -> int:
        @cache
        def dfs(i, j):
            if i > j or i >= arrLen or i < 0 or j < 0:
                return 0
            if i == 0 and j == 0:
                return 1
            ans = 0
            for k in range(-1, 2):
                ans += dfs(i + k, j - 1)
                ans %= mod
            return ans

        mod = 10**9 + 7
        return dfs(0, steps)
