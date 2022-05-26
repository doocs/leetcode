class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if not prices:
            return 0
        res = 0
        min_price = prices[0]
        for price in prices:
            min_price = min(min_price, price)
            res = max(res, price - min_price)
        return res