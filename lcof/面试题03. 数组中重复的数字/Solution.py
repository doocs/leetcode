class Solution:
    def findRepeatNumber(self, nums: list[int]) -> int:
        for a, b in pairwise(sorted(nums)):
            if a == b:
                return a
