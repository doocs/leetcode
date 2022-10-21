class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [[0] * 3 for _ in range(n + 1)]
        for i in range(1, n + 1):
            dp[i][0] = dp[i - 1][0]
            dp[i][1] = dp[i - 1][1]
            dp[i][2] = dp[i - 1][2]
            if nums[i - 1] % 3 == 0:
                dp[i][0] += nums[i - 1]
                if dp[i][1]:
                    dp[i][1] += nums[i - 1]
                if dp[i][2]:
                    dp[i][2] += nums[i - 1]
            elif nums[i - 1] % 3 == 1:
                if dp[i - 1][2]:
                    dp[i][0] = max(dp[i][0], dp[i - 1][2] + nums[i - 1])
                dp[i][1] = max(dp[i][1], dp[i - 1][0] + nums[i - 1])
                if dp[i - 1][1]:
                    dp[i][2] = max(dp[i][2], dp[i - 1][1] + nums[i - 1])
            else:
                if dp[i - 1][1]:
                    dp[i][0] = max(dp[i][0], dp[i - 1][1] + nums[i - 1])
                if dp[i - 1][2]:
                    dp[i][1] = max(dp[i][1], dp[i - 1][2] + nums[i - 1])
                dp[i][2] = max(dp[i][2], dp[i - 1][0] + nums[i - 1])
        return dp[n][0]
