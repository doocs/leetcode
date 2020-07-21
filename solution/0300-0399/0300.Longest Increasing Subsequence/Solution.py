class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        dp = [0 for _ in range(n)]
        dp[0] = 1
        res = 1
        for i in range(n):
            max_val = 0
            for j in range(0, i):
                if nums[j] < nums[i]:
                    max_val = max(max_val, dp[j])
                dp[i] = max_val + 1
                res = max(res, dp[i])
        return res
