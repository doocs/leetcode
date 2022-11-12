class Solution:
    def numTilings(self, n: int) -> int:
        @cache
        def dfs(i, j):
            if i > n or j > n:
                return 0
            if i == n and j == n:
                return 1
            ans = 0
            if i == j:
                ans = (
                    dfs(i + 2, j + 2)
                    + dfs(i + 1, j + 1)
                    + dfs(i + 2, j + 1)
                    + dfs(i + 1, j + 2)
                )
            elif i > j:
                ans = dfs(i, j + 2) + dfs(i + 1, j + 2)
            else:
                ans = dfs(i + 2, j) + dfs(i + 2, j + 1)
            return ans % mod

        mod = 10**9 + 7
        return dfs(0, 0)
