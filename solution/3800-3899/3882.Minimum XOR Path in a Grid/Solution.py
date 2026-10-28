class Solution:
    def minCost(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        mx = 1 << (max(max(row) for row in grid)).bit_length()
        f = [[[False] * mx for _ in range(n)] for _ in range(m)]
        f[0][0][grid[0][0]] = True
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if i > 0 or j > 0:
                    for k in range(mx):
                        if i > 0 and f[i - 1][j][k ^ x]:
                            f[i][j][k] = True
                        if j > 0 and f[i][j - 1][k ^ x]:
                            f[i][j][k] = True
        return next(k for k in range(mx) if f[m - 1][n - 1][k])
