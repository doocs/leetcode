class Solution:
    def xorBeauty(self, nums: list[int]) -> int:
        return reduce(xor, nums)
