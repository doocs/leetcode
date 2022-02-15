class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        n = len(prices)
        if n < 2:
            return 0
        dp = [[[0] * 2 for _ in range(k + 1)] for _ in range(n)]
        for i in range(1, k + 1):
            dp[0][i][1] = -prices[0]
        for i in range(1, n):
            for j in range(1, k + 1):
                dp[i][j][0] = max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0])
                dp[i][j][1] = max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1])
        return dp[-1][k][0]
