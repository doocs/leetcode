class Solution:
    def maxA(self, n: int) -> int:
        dp = list(range(n + 1))
        for i in range(3, n + 1):
            for j in range(2, i - 1):
                dp[i] = max(dp[i], dp[j - 1] * (i - j))
        return dp[-1]
