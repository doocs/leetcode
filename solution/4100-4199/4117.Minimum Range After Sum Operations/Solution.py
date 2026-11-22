class Solution:
    def minRange(self, a: int, b: int, c: int) -> int:
        nums = [a, b, c]
        nums.sort()
        return min(nums[2] - nums[0], nums[1])
