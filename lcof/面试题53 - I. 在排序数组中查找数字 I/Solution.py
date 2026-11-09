class Solution:
    def search(self, nums: list[int], target: int) -> int:
        l = bisect_left(nums, target)
        r = bisect_right(nums, target)
        return r - l
