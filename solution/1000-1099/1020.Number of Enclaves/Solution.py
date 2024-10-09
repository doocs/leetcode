class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int):
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    dfs(x, y)

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        for j in range(n):
            for i in (0, m - 1):
                if grid[i][j]:
                    dfs(i, j)
        for i in range(m):
            for j in (0, n - 1):
                if grid[i][j]:
                    dfs(i, j)
        return sum(sum(row) for row in grid)
