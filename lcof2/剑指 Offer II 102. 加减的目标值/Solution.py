class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s - target < 0 or (s - target) % 2 != 0:
            return 0
        target = (s - target) // 2 + 1
        n = len(nums) + 1
        dp = [0] * target
        dp[0] = 1
        for i in range(1, n):
            for j in range(target - 1, nums[i - 1] - 1, -1):
                dp[j] += dp[j - nums[i - 1]]
        return dp[-1]
