class Solution:
    def maximumCount(self, nums: list[int]) -> int:
        a = len(nums) - bisect_left(nums, 1)
        b = bisect_left(nums, 0)
        return max(a, b)
