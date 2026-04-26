class Solution:
    def minOperations(self, nums: list[int]) -> int:
        return sum(max(a - b, 0) for a, b in pairwise(nums))
