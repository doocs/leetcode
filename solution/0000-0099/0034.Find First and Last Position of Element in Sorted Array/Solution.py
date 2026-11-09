class Solution:
    def searchRange(self, nums: list[int], target: int) -> list[int]:
        l = bisect_left(nums, target)
        r = bisect_left(nums, target + 1)
        return [-1, -1] if l == r else [l, r - 1]
