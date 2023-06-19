class Solution:
    def rob(self, nums: List[int]) -> int:
        f, g = 0, nums[0]
        for x in nums[1:]:
            f, g = g, max(f + x, g)
        return g
