class Solution:
    def getMoneyAmount(self, n: int) -> int:
        dp = [[0] * (n + 10) for _ in range(n + 10)]
        for l in range(2, n + 1):
            for i in range(1, n - l + 2):
                j = i + l - 1
                dp[i][j] = inf
                for k in range(i, j + 1):
                    t = max(dp[i][k - 1], dp[k + 1][j]) + k
                    dp[i][j] = min(dp[i][j], t)
        return dp[1][n]
