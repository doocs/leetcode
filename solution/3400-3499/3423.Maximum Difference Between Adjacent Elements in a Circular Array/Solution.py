class Solution:
    def maxAdjacentDistance(self, nums: list[int]) -> int:
        return max(abs(a - b) for a, b in pairwise(nums + [nums[0]]))
