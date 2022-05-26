class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        res = f = nums[0]
        for i in range(1, n):
            f = nums[i] + max(f, 0)
            res = max(res, f)
        return res
