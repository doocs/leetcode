class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        ans = prices[:]
        for i, v in enumerate(prices):
            while stk and prices[stk[-1]] >= v:
                ans[stk.pop()] -= v
            stk.append(i)
        return ans
