class Solution:
    def minOperations(self, nums: list[int]) -> int:
        ans = n = len(nums)
        nums = sorted(set(nums))
        for i, v in enumerate(nums):
            j = bisect_right(nums, v + n - 1)
            ans = min(ans, n - (j - i))
        return ans
