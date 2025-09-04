from typing import List


class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        mx = max(nums)
        mn = min(nums)

        diff = mx - mn
        dp = [[0] * (diff + 1) for _ in range(mx + 1)]
        ans = 0

        for n in nums:
            maxnum = 1
            for i in range(diff, -1, -1):
                if n + i <= mx and dp[n + i][i] + 1 > maxnum:
                    maxnum = dp[n + i][i] + 1
                if n - i >= 0 and dp[n - i][i] + 1 > maxnum:
                    maxnum = dp[n - i][i] + 1

                dp[n][i] = maxnum
                ans = max(maxnum, ans)

        return ans
