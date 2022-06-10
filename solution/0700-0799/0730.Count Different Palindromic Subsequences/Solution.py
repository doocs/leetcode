class Solution:
    def countPalindromicSubsequences(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        dp = [[[0] * 4 for _ in range(n)] for _ in range(n)]
        for i, c in enumerate(s):
            dp[i][i][ord(c) - ord('a')] = 1
        for l in range(2, n + 1):
            for i in range(n - l + 1):
                j = i + l - 1
                for c in 'abcd':
                    k = ord(c) - ord('a')
                    if s[i] == s[j] == c:
                        dp[i][j][k] = 2 + sum(dp[i + 1][j - 1])
                    elif s[i] == c:
                        dp[i][j][k] = dp[i][j - 1][k]
                    elif s[j] == c:
                        dp[i][j][k] = dp[i + 1][j][k]
                    else:
                        dp[i][j][k] = dp[i + 1][j - 1][k]
        return sum(dp[0][-1]) % mod
