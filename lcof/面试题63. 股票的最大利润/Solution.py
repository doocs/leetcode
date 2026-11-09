class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        mi, ans = inf, 0
        for x in prices:
            ans = max(ans, x - mi)
            mi = min(mi, x)
        return ans
