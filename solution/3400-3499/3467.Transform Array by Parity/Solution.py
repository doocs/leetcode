class Solution:
    def transformArray(self, nums: list[int]) -> list[int]:
        even = sum(x % 2 == 0 for x in nums)
        for i in range(even):
            nums[i] = 0
        for i in range(even, len(nums)):
            nums[i] = 1
        return nums
