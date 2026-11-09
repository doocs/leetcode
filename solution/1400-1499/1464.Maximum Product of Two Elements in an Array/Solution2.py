class Solution:
    def maxProduct(self, nums: list[int]) -> int:
        nums.sort()
        return (nums[-1] - 1) * (nums[-2] - 1)
