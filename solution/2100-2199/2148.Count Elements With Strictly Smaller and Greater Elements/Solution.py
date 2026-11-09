class Solution:
    def countElements(self, nums: list[int]) -> int:
        mi, mx = min(nums), max(nums)
        return sum(mi < x < mx for x in nums)
