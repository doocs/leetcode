class Solution:
    def findErrorNums(self, nums: list[int]) -> list[int]:
        n = len(nums)
        s1 = (1 + n) * n // 2
        s2 = sum(set(nums))
        s = sum(nums)
        return [s - s2, s1 - s2]
