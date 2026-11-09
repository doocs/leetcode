class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        return sum(max(0, b - a) for a, b in pairwise(prices))
