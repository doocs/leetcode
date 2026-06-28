class Solution:
    def maxSum(self, nums: list[int], k: int, mul: int) -> int:
        nums.sort()
        n = len(nums)
        ans = 0
        for i in range(n - 1, n - 1 - k, -1):
            ans += nums[i] * max(1, mul)
            mul -= 1
        return ans
