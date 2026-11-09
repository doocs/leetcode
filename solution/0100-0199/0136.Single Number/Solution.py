class Solution:
    def singleNumber(self, nums: list[int]) -> int:
        return reduce(xor, nums)
