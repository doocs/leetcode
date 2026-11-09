class Solution:
    def maximumMedianSum(self, nums: list[int]) -> int:
        nums.sort()
        return sum(nums[len(nums) // 3 :: 2])
