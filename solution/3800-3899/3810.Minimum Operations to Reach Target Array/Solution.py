class Solution:
    def minOperations(self, nums: List[int], target: List[int]) -> int:
        s = {x for x, y in zip(nums, target) if x != y}
        return len(s)
