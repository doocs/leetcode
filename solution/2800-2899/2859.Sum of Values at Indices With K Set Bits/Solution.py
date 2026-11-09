class Solution:
    def sumIndicesWithKSetBits(self, nums: list[int], k: int) -> int:
        return sum(x for i, x in enumerate(nums) if i.bit_count() == k)
