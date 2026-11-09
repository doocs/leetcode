class Solution:
    def orArray(self, nums: list[int]) -> list[int]:
        return [a | b for a, b in pairwise(nums)]
