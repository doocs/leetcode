class Solution:
    def alternatingSum(self, nums: list[int]) -> int:
        return sum(nums[0::2]) - sum(nums[1::2])
