class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        for i in range(2, n):
            if nums[i] + nums[i - 2] == (nums[i - 1] << 1):
                dp[i] = 1 + dp[i - 1]
        return sum(dp)
