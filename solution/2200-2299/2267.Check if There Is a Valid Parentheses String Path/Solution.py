class Solution:
    def hasValidPath(self, grid: List[List[str]]) -> bool:
        @cache
        def dfs(i, j, t):
            if grid[i][j] == '(':
                t += 1
            else:
                t -= 1
            if t < 0:
                return False
            if i == m - 1 and j == n - 1:
                return t == 0
            for x, y in [(i + 1, j), (i, j + 1)]:
                if x < m and y < n and dfs(x, y, t):
                    return True
            return False

        m, n = len(grid), len(grid[0])
        return dfs(0, 0, 0)
