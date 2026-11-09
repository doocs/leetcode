class Solution:
    def smallerNumbersThanCurrent(self, nums: list[int]) -> list[int]:
        arr = sorted(nums)
        return [bisect_left(arr, x) for x in nums]
