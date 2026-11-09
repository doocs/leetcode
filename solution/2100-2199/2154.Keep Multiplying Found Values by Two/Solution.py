class Solution:
    def findFinalValue(self, nums: list[int], original: int) -> int:
        s = set(nums)
        while original in s:
            original <<= 1
        return original
