class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[[0] * n for _ in range(n)] for _ in range(m)]
        valid = [[[False] * n for _ in range(n)] for _ in range(m)]
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1]
        valid[0][0][n - 1] = True
        for i in range(1, m):
            for j1 in range(n):
                for j2 in range(n):
                    t = grid[i][j1]
                    if j1 != j2:
                        t += grid[i][j2]
                    ok = False
                    for y1 in range(j1 - 1, j1 + 2):
                        for y2 in range(j2 - 1, j2 + 2):
                            if 0 <= y1 < n and 0 <= y2 < n and valid[i - 1][y1][y2]:
                                dp[i][j1][j2] = max(
                                    dp[i][j1][j2], dp[i - 1][y1][y2] + t
                                )
                                ok = True
                    valid[i][j1][j2] = ok
        return max(dp[m - 1][j1][j2] for j1 in range(n) for j2 in range(n))
