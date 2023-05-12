class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        s = set(nums)
        return max((x for x in s if -x in s), default=-1)
