class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        n = len(grid)
        dp = [[[float('-inf')] * n for _ in range(n)] for _ in range((n << 1) -1 )]
        dp[0][0][0] = grid[0][0]
        for k in range(1, (n << 1) - 1):
            for i1 in range(n):
                for i2 in range(n):
                    j1, j2 = k - i1, k - i2
                    if j1 >= 0 and j1 < n and j2 >= 0 and j2 < n:
                        if grid[i1][j1] == -1 or grid[i2][j2] == -1:
                            continue
                        t = grid[i1][j1]
                        if i1 != i2:
                            t += grid[i2][j2]
                        for p1 in range(i1 - 1, i1 + 1):
                            for p2 in range(i2 - 1, i2 + 1):
                                if p1 >= 0 and p2 >= 0:
                                    dp[k][i1][i2] = max(dp[k][i1][i2], dp[k - 1][p1][p2] + t)
        return max(dp[-1][-1][-1], 0)
