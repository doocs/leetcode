class Solution:
    def minimumOperations(self, nums: list[int]) -> int:
        return len({x for x in nums if x})
