class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        ans = prices[:]
        for i in range(len(prices) - 1, -1, -1):
            while stk and prices[stk[-1]] > prices[i]:
                stk.pop()
            if stk:
                ans[i] -= prices[stk[-1]]
            stk.append(i)
        return ans
