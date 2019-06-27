class Solution:
    def superEggDrop(self, K: int, N: int) -> int:
        dp = [1] * (K)
        while dp[K - 1] < N:
            for i in range(K - 1, 0, -1):
                dp[i] = dp[i] + dp[i - 1] + 1
            dp[0] = dp[0] + 1
        return dp[0]
