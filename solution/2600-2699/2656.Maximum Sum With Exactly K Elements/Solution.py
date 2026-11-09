class Solution:
    def maximizeSum(self, nums: list[int], k: int) -> int:
        x = max(nums)
        return k * x + k * (k - 1) // 2
