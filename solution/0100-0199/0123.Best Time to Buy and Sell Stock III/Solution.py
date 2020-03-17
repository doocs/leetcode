class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        length = len(prices)
        if 0 == length:
            return 0
        dp = [([0] * length) for i in range(3)]
        for i in range(1, 3, 1):
            maxdiff = -((1 << 31) - 1)
            for j in range(1, length, 1):
                maxdiff = max(maxdiff, dp[i-1][j-1] - prices[j-1])
                dp[i][j] = max(dp[i][j - 1], maxdiff + prices[j])
        return dp[2][length - 1]