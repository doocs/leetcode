class Solution:
    def maxAscendingSum(self, nums: List[int]) -> int:
        res, cur = 0, nums[0]
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                cur += nums[i]
            else:
                res = max(res, cur)
                cur = nums[i]
        res = max(res, cur)
        return res
