class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        dp = [0] * 3
        for v in nums:
            a, b, c = dp[0] + v, dp[1] + v, dp[2] + v
            dp[a % 3] = max(dp[a % 3], a)
            dp[b % 3] = max(dp[b % 3], b)
            dp[c % 3] = max(dp[c % 3], c)
        return dp[0]
