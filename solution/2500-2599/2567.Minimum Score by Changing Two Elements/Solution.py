class Solution:
    def minimizeSum(self, nums: list[int]) -> int:
        nums.sort()
        a = abs(nums[-3] - nums[0])
        b = abs(nums[-2] - nums[1])
        c = abs(nums[-1] - nums[2])
        return min(a, b, c)
