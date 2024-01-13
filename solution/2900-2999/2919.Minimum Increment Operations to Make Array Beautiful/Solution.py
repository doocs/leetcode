class Solution:
    def minIncrementOperations(self, nums: List[int], k: int) -> int:
        f = g = h = 0
        for x in nums:
            f, g, h = g, h, min(f, g, h) + max(k - x, 0)
        return min(f, g, h)
