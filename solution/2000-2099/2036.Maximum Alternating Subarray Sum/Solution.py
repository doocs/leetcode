class Solution:
    def maximumAlternatingSubarraySum(self, nums: List[int]) -> int:
        ans = f = g = -inf
        for x in nums:
            f, g = max(g, 0) + x, f - x
            ans = max(ans, f, g)
        return ans
