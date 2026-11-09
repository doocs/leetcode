class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        return reduce(xor, (i ^ v for i, v in enumerate(nums, 1)))
