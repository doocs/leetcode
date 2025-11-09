class Solution:
    def minMoves(self, nums: List[int]) -> int:
        n = len(nums)
        mx = max(nums)
        s = sum(nums)
        return mx * n - s
