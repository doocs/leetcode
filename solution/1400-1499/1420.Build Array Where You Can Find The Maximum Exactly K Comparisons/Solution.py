class Solution:
    def numOfArrays(self, n: int, m: int, k: int) -> int:
        if k == 0:
            return 0
        dp = [[[0] * (m + 1) for _ in range(k + 1)] for _ in range(n + 1)]
        mod = 10**9 + 7
        for i in range(1, m + 1):
            dp[1][1][i] = 1
        for i in range(2, n + 1):
            for c in range(1, min(k + 1, i + 1)):
                for j in range(1, m + 1):
                    dp[i][c][j] = dp[i - 1][c][j] * j
                    for j0 in range(1, j):
                        dp[i][c][j] += dp[i - 1][c - 1][j0]
                        dp[i][c][j] %= mod
        ans = 0
        for i in range(1, m + 1):
            ans += dp[n][k][i]
            ans %= mod
        return ans
