class Solution:
    def massage(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        if n < 2:
            return nums[0]
        dp = [0 for _ in range(n)]
        dp[0], dp[1] = nums[0], max(nums[0], nums[1])
        for i in range(2, n):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
        return dp[n - 1]
