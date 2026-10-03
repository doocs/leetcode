class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        f = [[0] * 2 for _ in range(n + 2)]
        for i in range(n - 1, -1, -1):
            for j in range(2):
                ans = f[i + 1][j]
                if j:
                    ans = max(ans, prices[i] + f[i + 2][0])
                else:
                    ans = max(ans, -prices[i] + f[i + 1][1])
                f[i][j] = ans
        return f[0][0]
