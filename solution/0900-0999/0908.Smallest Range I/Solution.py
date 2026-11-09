class Solution:
    def smallestRangeI(self, nums: list[int], k: int) -> int:
        mx, mi = max(nums), min(nums)
        return max(0, mx - mi - k * 2)
