class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        return len({x for x in nums if x})
