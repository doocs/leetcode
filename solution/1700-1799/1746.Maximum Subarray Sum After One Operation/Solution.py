class Solution:
    def maxSumAfterOperation(self, nums: List[int]) -> int:
        f = g = 0
        ans = -inf
        for x in nums:
            ff = max(f, 0) + x
            gg = max(max(f, 0) + x * x, g + x)
            f, g = ff, gg
            ans = max(ans, f, g)
        return ans
