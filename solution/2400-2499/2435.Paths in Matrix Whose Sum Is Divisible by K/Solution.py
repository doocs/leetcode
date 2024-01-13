class Solution:
    def numberOfPaths(self, grid: List[List[int]], k: int) -> int:
        @cache
        def dfs(i, j, s):
            if i < 0 or i >= m or j < 0 or j >= n:
                return 0
            s = (s + grid[i][j]) % k
            if i == m - 1 and j == n - 1:
                return int(s == 0)
            ans = dfs(i + 1, j, s) + dfs(i, j + 1, s)
            return ans % mod

        m, n = len(grid), len(grid[0])
        mod = 10**9 + 7
        ans = dfs(0, 0, 0)
        dfs.cache_clear()
        return ans
