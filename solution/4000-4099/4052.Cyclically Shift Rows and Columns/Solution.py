class Solution:
    def cyclicShift(
        self, n: int, grid: list[list[int]], rowShift: list[int], colShift: list[int]
    ) -> list[list[int]]:
        t = [[0] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                t[i][(j - rowShift[i] + n) % n] = grid[i][j]
        ans = [[0] * n for _ in range(n)]
        for j in range(n):
            for i in range(n):
                ans[(i - colShift[j] + n) % n][j] = t[i][j]
        return ans
