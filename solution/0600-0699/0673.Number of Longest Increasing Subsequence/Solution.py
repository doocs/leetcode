class Solution:
    def findNumberOfLIS(self, nums: List[int]) -> int:
        maxLen, ans, n = 0, 0, len(nums)
        dp, cnt = [1] * n, [1] * n
        for i in range(n):
            for j in range(i):
                if nums[i] > nums[j]:
                    if dp[j] + 1 > dp[i]:
                        dp[i] = dp[j] + 1
                        cnt[i] = cnt[j]
                    elif dp[j] + 1 == dp[i]:
                        cnt[i] += cnt[j]
            if dp[i] > maxLen:
                maxLen = dp[i]
                ans = cnt[i]
            elif dp[i] == maxLen:
                ans += cnt[i]
        return ans
