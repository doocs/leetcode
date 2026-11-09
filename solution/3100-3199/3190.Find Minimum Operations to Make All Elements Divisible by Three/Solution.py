class Solution:
    def minimumOperations(self, nums: list[int]) -> int:
        return sum(x % 3 != 0 for x in nums)
