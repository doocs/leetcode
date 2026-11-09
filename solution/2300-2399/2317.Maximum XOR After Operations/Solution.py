class Solution:
    def maximumXOR(self, nums: list[int]) -> int:
        return reduce(or_, nums)
