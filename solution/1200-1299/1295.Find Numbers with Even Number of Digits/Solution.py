class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum(len(str(v)) % 2 == 0 for v in nums)
