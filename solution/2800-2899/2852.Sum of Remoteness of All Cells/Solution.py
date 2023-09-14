class Solution:
    def sumRemoteness(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> (int, int):
            s, t = grid[i][j], 1
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] > 0:
                    s1, t1 = dfs(x, y)
                    s, t = s + s1, t + t1
            return s, t

        n = len(grid)
        dirs = (-1, 0, 1, 0, -1)
        cnt = sum(x > 0 for row in grid for x in row)
        ans = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x > 0:
                    s, t = dfs(i, j)
                    ans += (cnt - t) * s
        return ans
