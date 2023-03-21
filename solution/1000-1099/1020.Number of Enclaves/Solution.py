class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    dfs(x, y)

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        for i in range(m):
            for j in range(n):
                if grid[i][j] and (i == 0 or i == m - 1 or j == 0 or j == n - 1):
                    dfs(i, j)
        return sum(v for row in grid for v in row)
