class Solution:
    def largestSubarray(self, nums: list[int], k: int) -> list[int]:
        i = nums.index(max(nums[: len(nums) - k + 1]))
        return nums[i : i + k]
