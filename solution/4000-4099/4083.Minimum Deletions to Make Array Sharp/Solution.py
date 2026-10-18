class Solution:
    def minDeletions(self, nums: list[int]) -> int:
        return sum(x == y for x, y in pairwise(nums))
