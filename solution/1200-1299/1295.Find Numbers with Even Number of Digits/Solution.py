class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum(len(str(x)) % 2 == 0 for x in nums)
