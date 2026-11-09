class Solution:
    def reverseSubmatrix(
        self, grid: list[list[int]], x: int, y: int, k: int
    ) -> list[list[int]]:
        for i in range(x, x + k // 2):
            i2 = x + k - 1 - (i - x)
            for j in range(y, y + k):
                grid[i][j], grid[i2][j] = grid[i2][j], grid[i][j]
        return grid
