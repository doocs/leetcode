class Solution:
    def dominantIndex(self, nums: list[int]) -> int:
        x, y = nlargest(2, nums)
        return nums.index(x) if x >= 2 * y else -1
