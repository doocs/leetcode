class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = s = -inf
        for v in nums:
            s = max(s, 0) + v
            ans = max(ans, s)
        return ans
