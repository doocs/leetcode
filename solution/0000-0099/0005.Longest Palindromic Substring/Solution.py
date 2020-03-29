class Solution:
    def longestPalindrome(self, s: str) -> str:
        dp = [[0 for _ in range(len(s) + 1)] for _ in range(len(s) + 1)]

        for i in range(len(s)):
            dp[i][i] = 1
        
        for i in range(len(s) - 1, -1, -1):
            for j in range(i + 1, len(s)):
                if s[i] == s[j]:
                    if i + 1 == j:
                        dp[i][j] = 2
                    elif i + 2 == j:
                        dp[i][j] = 3
                    else:
                        if dp[i + 1][j - 1] != 0:
                            dp[i][j] = 2 + dp[i + 1][j - 1]
        maximum = max(map(max, dp))
        if maximum == 0:
            return ""
        for i in range(len(s)):
            for j in range(len(s)):
                if dp[i][j] == maximum:
                    return s[i: j + 1]
