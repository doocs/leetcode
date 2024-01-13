class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[0] * (n + 1) for _ in range(2)]
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                f[i & 1][j] = max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + v
        return f[m & 1][n]
