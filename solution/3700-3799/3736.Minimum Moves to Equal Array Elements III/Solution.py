class Solution:
    def minMoves(self, nums: list[int]) -> int:
        n = len(nums)
        mx = max(nums)
        s = sum(nums)
        return mx * n - s
