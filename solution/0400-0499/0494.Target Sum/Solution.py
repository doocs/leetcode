class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target or (s - target) % 2 != 0:
            return 0
        m, n = len(nums) + 1, (s - target) // 2 + 1
        dp = [0] * n
        dp[0] = 1
        for i in range(1, m):
            for j in range(n - 1, nums[i - 1] - 1, -1):
                dp[j] += dp[j - nums[i - 1]]
        return dp[-1]
