class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [False] * n
        dp[0] = True
        if nums[0] < n:
            dp[nums[0]] = True

        for i in range(1, m):
            for j in range(n - 1, nums[i] - 1, -1):
                dp[j] = dp[j] or dp[j - nums[i]]
        return dp[-1]
