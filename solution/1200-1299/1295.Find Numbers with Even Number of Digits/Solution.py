class Solution:
    def findNumbers(self, nums: list[int]) -> int:
        return sum(len(str(x)) % 2 == 0 for x in nums)
