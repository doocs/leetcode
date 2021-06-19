class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        def dfs(grid, i, j, m, n):
            if i < 0 or i >= m or j < 0 or j >= n or grid[i][j] == 0:
                return 0
            grid[i][j] = 0
            res = 1
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                res += dfs(grid, i + x, j + y, m, n)
            return res
        
        m, n = len(grid), len(grid[0])
        res = 0
        for i in range(m):
            for j in range(n):
                t = dfs(grid, i, j, m, n)
                res = max(res, t)
        return res
