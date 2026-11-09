class Solution:
    def minOperations(self, nums: list[int], target: list[int]) -> int:
        s = {x for x, y in zip(nums, target) if x != y}
        return len(s)
