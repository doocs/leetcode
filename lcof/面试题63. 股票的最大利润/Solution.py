class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if len(prices) == 0:
            return 0
        mi = prices[0]
        res = 0
        for val in prices[1:]:
            res = max(res, val - mi)
            mi = min(mi, val)
        return res
