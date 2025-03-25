class Solution:
    def maxScore(self, nums: List[int]) -> int:
        s = sum(nums)
        if len(nums) & 1:
            return s - min(nums)
        return s - min(a + b for a, b in pairwise(nums))
