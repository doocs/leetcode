class Solution:
    def maxTrailingZeros(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        r2 = [[0] * (n + 1) for _ in range(m + 1)]
        c2 = [[0] * (n + 1) for _ in range(m + 1)]
        r5 = [[0] * (n + 1) for _ in range(m + 1)]
        c5 = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid, 1):
            for j, x in enumerate(row, 1):
                s2 = s5 = 0
                while x % 2 == 0:
                    x //= 2
                    s2 += 1
                while x % 5 == 0:
                    x //= 5
                    s5 += 1
                r2[i][j] = r2[i][j - 1] + s2
                c2[i][j] = c2[i - 1][j] + s2
                r5[i][j] = r5[i][j - 1] + s5
                c5[i][j] = c5[i - 1][j] + s5
        ans = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                a = min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j])
                b = min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j])
                c = min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j])
                d = min(
                    r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                    r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j],
                )
                ans = max(ans, a, b, c, d)
        return ans
