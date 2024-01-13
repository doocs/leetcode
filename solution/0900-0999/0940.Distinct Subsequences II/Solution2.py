class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        dp = [0] * 26
        for c in s:
            i = ord(c) - ord('a')
            dp[i] = sum(dp) % mod + 1
        return sum(dp) % mod
