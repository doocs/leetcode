class Solution:
    def sortByAbsoluteValue(self, nums: list[int]) -> list[int]:
        return sorted(nums, key=lambda x: abs(x))
