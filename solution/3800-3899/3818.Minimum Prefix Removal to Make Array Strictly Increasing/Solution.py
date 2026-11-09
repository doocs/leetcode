class Solution:
    def minimumPrefixLength(self, nums: list[int]) -> int:
        for i in range(len(nums) - 1, 0, -1):
            if nums[i - 1] >= nums[i]:
                return i
        return 0
