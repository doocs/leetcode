class Solution:
    def totalSteps(self, nums: List[int]) -> int:
        stk = []
        ans, n = 0, len(nums)
        dp = [0] * n
        for i in range(n - 1, -1, -1):
            while stk and nums[i] > nums[stk[-1]]:
                dp[i] = max(dp[i] + 1, dp[stk.pop()])
            stk.append(i)
        return max(dp)
