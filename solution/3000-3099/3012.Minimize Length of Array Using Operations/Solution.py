class Solution:
    def minimumArrayLength(self, nums: list[int]) -> int:
        mi = min(nums)
        if any(x % mi for x in nums):
            return 1
        return (nums.count(mi) + 1) // 2
