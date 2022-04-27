class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target or (s - target) % 2 != 0:
            return 0
        n = (s - target) // 2
        dp = [0] * (n + 1)
        dp[0] = 1
        for v in nums:
            for j in range(n, v - 1, -1):
                dp[j] += dp[j - v]
        return dp[-1]
