class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        n = len(nums)
        res = f = 1
        for i in range(1, n):
            f = 1 + (f if nums[i - 1] < nums[i] else 0)
            res = max(res, f)
        return res
