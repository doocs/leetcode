class Solution:
    def exchange(self, nums: list[int]) -> list[int]:
        j = 0
        for i, x in enumerate(nums):
            if x & 1:
                nums[i], nums[j] = nums[j], nums[i]
                j += 1
        return nums
