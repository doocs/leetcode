class Solution:
    def maximumAlternatingSubarraySum(self, nums: List[int]) -> int:
        ans = nums[0]
        a, b = nums[0], -inf
        for v in nums[1:]:
            a, b = max(v, b + v), a - v
            ans = max(ans, a, b)
        return ans
