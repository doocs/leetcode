class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        @cache
        def dfs(i, j, k):
            if i >= m or j >= n:
                return False
            k += grid[i][j]
            if k > s or i + j + 1 - k > s:
                return False
            if i == m - 1 and j == n - 1:
                return k == s
            return dfs(i + 1, j, k) or dfs(i, j + 1, k)

        m, n = len(grid), len(grid[0])
        s = m + n - 1
        if s & 1:
            return False
        s >>= 1
        return dfs(0, 0, 0)
