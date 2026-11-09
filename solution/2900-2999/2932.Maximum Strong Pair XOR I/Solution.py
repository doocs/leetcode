class Solution:
    def maximumStrongPairXor(self, nums: list[int]) -> int:
        return max(x ^ y for x in nums for y in nums if abs(x - y) <= min(x, y))
