class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            if not (0 <= i < m and 0 <= j < n and grid[i][j]):
                return 0
            t = grid[i][j]
            grid[i][j] = 0
            res = 0
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                res = max(res, t + dfs(i + a, j + b))
            grid[i][j] = t
            return res

        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(m):
            for j in range(n):
                ans = max(ans, dfs(i, j))
        return ans
