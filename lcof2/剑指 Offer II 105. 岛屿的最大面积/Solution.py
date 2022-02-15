class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            grid[i][j] = 0
            ans = 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    ans += dfs(x, y)
            return ans

        m, n = len(grid), len(grid[0])
        return max(
            [dfs(i, j) for i in range(m) for j in range(n) if grid[i][j] == 1],
            default=0,
        )
