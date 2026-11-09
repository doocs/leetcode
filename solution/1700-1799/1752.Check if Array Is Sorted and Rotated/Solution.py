class Solution:
    def check(self, nums: list[int]) -> bool:
        return sum(nums[i - 1] > x for i, x in enumerate(nums)) <= 1
