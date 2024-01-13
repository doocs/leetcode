class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        ans = []
        for i, v in enumerate(prices):
            ans.append(v)
            for j in range(i + 1, len(prices)):
                if prices[j] <= v:
                    ans[-1] -= prices[j]
                    break
        return ans
