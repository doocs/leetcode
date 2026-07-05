class Solution:
    def isMiddleElementUnique(self, nums: list[int]) -> bool:
        return nums.count(nums[len(nums) // 2]) == 1
