class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        def dfs(i: int, j: int) -> int:
            s = grid[i][j]
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    s += dfs(x, y)
            return s

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] and dfs(i, j) % k == 0:
                    ans += 1
        return ans
