class Solution:
    def finalElement(self, nums: list[int]) -> int:
        return max(nums[0], nums[-1])
