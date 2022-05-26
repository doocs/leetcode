class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        res, mi = 0, prices[0]
        for price in prices[1:]:
            res = max(res, price - mi)
            mi = min(mi, price)
        return res
