class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        dp = [[0] * 26 for _ in range(n + 1)]
        for i, c in enumerate(s, 1):
            k = ord(c) - ord('a')
            for j in range(26):
                if j == k:
                    dp[i][j] = sum(dp[i - 1]) % mod + 1
                else:
                    dp[i][j] = dp[i - 1][j]
        return sum(dp[-1]) % mod
