class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return sum(x != y for x, y in pairwise(nums))
