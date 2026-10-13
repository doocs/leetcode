class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        nums = [x * x for x in nums]
        nums.sort()
        ans, m = 0, len(nums) >> 1
        for i, x in enumerate(nums):
            ans += -x if i < m else x
        return ans
