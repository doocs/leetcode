class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        m, n = len(nums), s >> 1
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(1, m + 1):
            for j in range(n, nums[i - 1] - 1, -1):
                dp[j] = dp[j] or dp[j - nums[i - 1]]
        return dp[-1]
