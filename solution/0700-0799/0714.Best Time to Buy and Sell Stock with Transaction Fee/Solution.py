class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        f1, f2 = -prices[0], 0
        for price in prices[1:]:
            f1 = max(f1, f2 - price)
            f2 = max(f2, f1 + price - fee)
        return f2
