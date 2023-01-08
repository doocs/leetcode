class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = sum(v > 0 for v in nums)
        b = sum(v < 0 for v in nums)
        return max(a, b)
