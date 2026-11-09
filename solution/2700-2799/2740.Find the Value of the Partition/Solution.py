class Solution:
    def findValueOfPartition(self, nums: list[int]) -> int:
        nums.sort()
        return min(b - a for a, b in pairwise(nums))
