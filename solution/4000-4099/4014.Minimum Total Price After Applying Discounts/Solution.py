class Solution:
    def minPrice(self, prices: list[int], discounts: list[int]) -> float:
        prices.sort()
        discounts.sort()
        i, j = len(prices) - 1, len(discounts) - 1
        ans = 0
        while i >= 0 and j >= 0:
            ans += prices[i] * (100 - discounts[j]) / 100
            i -= 1
            j -= 1
        while i >= 0:
            ans += prices[i]
            i -= 1
        return ans
