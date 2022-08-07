class Solution:
    def numSquares(self, n: int) -> int:
        dp = [0] * (n + 1)
        for i in range(1, n + 1):
            j, mi = 1, inf
            while j * j <= i:
                mi = min(mi, dp[i - j * j])
                j += 1
            dp[i] = mi + 1
        return dp[-1]
