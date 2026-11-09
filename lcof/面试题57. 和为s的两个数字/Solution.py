class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        l, r = 0, len(nums) - 1
        while l < r:
            if nums[l] + nums[r] == target:
                return [nums[l], nums[r]]
            if nums[l] + nums[r] > target:
                r -= 1
            else:
                l += 1
