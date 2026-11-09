class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        n = len(nums)
        return (1 + n) * n // 2 - sum(nums)
