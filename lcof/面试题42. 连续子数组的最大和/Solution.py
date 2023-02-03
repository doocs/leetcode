class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans, f = -inf, 0
        for x in nums:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
