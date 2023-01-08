class Solution:
    def xorBeauty(self, nums: List[int]) -> int:
        return reduce(xor, nums)
