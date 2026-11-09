class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        return sum(range(len(nums) + 1)) - sum(nums)
