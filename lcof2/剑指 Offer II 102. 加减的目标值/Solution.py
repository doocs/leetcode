class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        if target < -1000 or target > 1000:
            return 0
        n = len(nums)
        dp = [[0] * 2001 for i in range(n)]
        dp[0][nums[0] + 1000] += 1
        dp[0][-nums[0] + 1000] += 1
        for i in range(1, n):
            for j in range(-1000, 1001):
                if dp[i - 1][j + 1000] > 0:
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
        return dp[n - 1][target + 1000]
