class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        if rows == 0 or cols == 0:
            return 0
        vals = [[0 for _ in range(cols)] for _ in range(rows)]
        vals[0][0] = grid[0][0]
        for i in range(1, rows):
            vals[i][0] = vals[i - 1][0] + grid[i][0]
        for j in range(1, cols):
            vals[0][j] = vals[0][j - 1] + grid[0][j]
        for i in range(1, rows):
            for j in range(1, cols):
                vals[i][j] = grid[i][j] + max(vals[i - 1][j], vals[i][j - 1])
        return vals[rows - 1][cols - 1]
