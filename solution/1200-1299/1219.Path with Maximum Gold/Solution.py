class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            if i < 0 or i >= len(grid) or j < 0 or j >= len(grid[0]) or grid[i][j] == 0 or vis[i][j]:
                return 0

            vis[i][j] = True
            t = 0
            for x, y in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                t = max(t, dfs(i + x, j + y))
            vis[i][j] = False
            return t + grid[i][j]

        m, n = len(grid), len(grid[0])
        ans = 0
        vis = [[False] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                ans = max(ans, dfs(i, j))
        return ans
