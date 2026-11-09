class Solution:
    def maxSubArray(self, nums: list[int]) -> int:
        ans, f = -inf, 0
        for x in nums:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
