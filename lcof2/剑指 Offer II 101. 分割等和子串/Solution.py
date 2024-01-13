class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [[False] * n for _ in range(m)]
        for i in range(m):
            dp[i][0] = True
        if nums[0] < n:
            dp[0][nums[0]] = True

        for i in range(1, m):
            for j in range(n):
                dp[i][j] = dp[i - 1][j]
                if not dp[i][j] and nums[i] <= j:
                    dp[i][j] = dp[i - 1][j - nums[i]]
        return dp[-1][-1]
