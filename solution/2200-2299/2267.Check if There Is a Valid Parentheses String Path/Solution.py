class Solution:
    def hasValidPath(self, grid: List[List[str]]) -> bool:
        @cache
        def dfs(i: int, j: int, k: int) -> bool:
            d = 1 if grid[i][j] == "(" else -1
            k += d
            if k < 0 or k > m - i + n - j:
                return False
            if i == m - 1 and j == n - 1:
                return k == 0
            for a, b in pairwise((0, 1, 0)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and dfs(x, y, k):
                    return True
            return False

        m, n = len(grid), len(grid[0])
        if (m + n - 1) % 2 or grid[0][0] == ")" or grid[m - 1][n - 1] == "(":
            return False
        return dfs(0, 0, 0)
