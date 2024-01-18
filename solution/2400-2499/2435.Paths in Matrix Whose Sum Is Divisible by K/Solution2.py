class Solution:
    def numberOfPaths(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[[0] * k for _ in range(n)] for _ in range(m)]
        dp[0][0][grid[0][0] % k] = 1
        mod = 10**9 + 7
        for i in range(m):
            for j in range(n):
                for s in range(k):
                    t = ((s - grid[i][j] % k) + k) % k
                    if i:
                        dp[i][j][s] += dp[i - 1][j][t]
                    if j:
                        dp[i][j][s] += dp[i][j - 1][t]
                    dp[i][j][s] %= mod
        return dp[-1][-1][0]
