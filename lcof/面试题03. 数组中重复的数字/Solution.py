class Solution:
    def findRepeatNumber(self, nums: List[int]) -> int:
        for a, b in pairwise(sorted(nums)):
            if a == b:
                return a
