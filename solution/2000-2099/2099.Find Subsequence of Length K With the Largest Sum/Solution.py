class Solution:
    def maxSubsequence(self, nums: list[int], k: int) -> list[int]:
        idx = sorted(range(len(nums)), key=lambda i: nums[i])[-k:]
        return [nums[i] for i in sorted(idx)]
