class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        res = t = nums[0]
        for i in range(1, len(nums)):
            t = nums[i] + (0 if t < 0 else t)
            res = max(res, t)
        return res
