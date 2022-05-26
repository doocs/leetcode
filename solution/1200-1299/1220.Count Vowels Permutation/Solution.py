class Solution:
    def countVowelPermutation(self, n: int) -> int:
        dp = (1, 1, 1, 1, 1)
        MOD = 1000000007
        for _ in range(n - 1):
            dp = (
                (dp[1] + dp[2] + dp[4]) % MOD,
                (dp[0] + dp[2]) % MOD,
                (dp[1] + dp[3]) % MOD,
                dp[2],
                (dp[2] + dp[3]) % MOD,
            )
        return sum(dp) % MOD
