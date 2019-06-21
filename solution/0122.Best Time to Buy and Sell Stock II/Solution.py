class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        maxprofit = 0
        for i in range(0, len(prices) - 1, 1):
            if prices[i + 1] > prices[i]:
                maxprofit += prices[i + 1] - prices[i]
        return maxprofit