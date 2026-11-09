class Solution:
    def massage(self, nums: list[int]) -> int:
        f = g = 0
        for x in nums:
            f, g = g + x, max(f, g)
        return max(f, g)
