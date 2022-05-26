class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            if not (0 <= i < m and 0 <= j < n and grid[i][j]):
                return 0
            t = grid[i][j]
            grid[i][j] = 0
            ans = t + max(
                dfs(i + a, j + b) for a, b in [[0, 1], [0, -1], [-1, 0], [1, 0]]
            )
            grid[i][j] = t
            return ans

        m, n = len(grid), len(grid[0])
        return max(dfs(i, j) for i in range(m) for j in range(n))
