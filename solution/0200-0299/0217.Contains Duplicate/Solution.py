class Solution:
    def containsDuplicate(self, nums: list[int]) -> bool:
        return any(a == b for a, b in pairwise(sorted(nums)))
