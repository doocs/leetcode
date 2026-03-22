class Solution:
    def maxProductPath(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[[0, 0] for _ in range(n)] for _ in range(m)]

        for i in range(m):
            for j in range(n):
                x = grid[i][j]
                if i == 0 and j == 0:
                    f[i][j][0] = x
                    f[i][j][1] = x
                    continue

                mn, mx = inf, -inf

                if i > 0:
                    a, b = f[i - 1][j]
                    mn = min(mn, a * x, b * x)
                    mx = max(mx, a * x, b * x)

                if j > 0:
                    a, b = f[i][j - 1]
                    mn = min(mn, a * x, b * x)
                    mx = max(mx, a * x, b * x)

                f[i][j][0], f[i][j][1] = mn, mx

        ans = f[m - 1][n - 1][1]
        mod = 10**9 + 7
        return -1 if ans < 0 else ans % mod
