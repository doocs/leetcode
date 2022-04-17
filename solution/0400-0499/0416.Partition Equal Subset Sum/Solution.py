class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        n = s >> 1
        dp = [False] * (n + 1)
        dp[0] = True
        for v in nums:
            for j in range(n, v - 1, -1):
                dp[j] = dp[j] or dp[j - v]
        return dp[-1]
