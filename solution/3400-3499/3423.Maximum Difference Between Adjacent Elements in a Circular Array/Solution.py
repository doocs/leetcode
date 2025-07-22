class Solution:
    def maxAdjacentDistance(self, nums: List[int]) -> int:
        return max(abs(a - b) for a, b in pairwise(nums + [nums[0]]))
