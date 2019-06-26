class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        length = len(prices)
        if 0 == length:
            return 0
        if k > (length / 2):
            maxprofit = 0
            for i in range(0, len(prices) - 1, 1):
                if prices[i + 1] > prices[i]:
                    maxprofit += prices[i + 1] - prices[i]
            return maxprofit
        m = k
        n = length
        dp = [[([0] * (2)) for i in range(m + 1)] for i in range(n + 1)]
        minNumber = -((1 << 31) - 1)
        for x in range(0, n + 1, 1):
            dp[x][0][0] = 0
            dp[x][0][1] = minNumber
        for y in range(1, m + 1, 1):
            dp[0][y][0] = 0
            dp[0][y][1] = minNumber
        for i in range(1, m + 1, 1):
            for j in range(1, length + 1, 1):
                dp[j][i][0] = max(dp[j - 1][i][0], dp[j - 1]
                                  [i][1] + prices[j - 1])
                dp[j][i][1] = max(dp[j - 1][i][1], dp[j - 1]
                                  [i - 1][0] - prices[j - 1])
        return dp[n][m][0]
