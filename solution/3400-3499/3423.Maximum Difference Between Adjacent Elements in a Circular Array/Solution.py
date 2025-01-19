class Solution:
    def maxAdjacentDistance(self, nums: List[int]) -> int:
        return max(max(abs(a - b) for a, b in pairwise(nums)), abs(nums[0] - nums[-1]))
