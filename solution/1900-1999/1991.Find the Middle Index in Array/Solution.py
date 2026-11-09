class Solution:
    def findMiddleIndex(self, nums: list[int]) -> int:
        l, r = 0, sum(nums)
        for i, x in enumerate(nums):
            r -= x
            if l == r:
                return i
            l += x
        return -1
