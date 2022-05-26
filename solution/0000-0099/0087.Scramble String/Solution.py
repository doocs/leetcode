class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        n = len(s1)
        dp = [[[False] * (n + 1) for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                dp[i][j][1] = s1[i] == s2[j]
        for l in range(2, n + 1):
            for i1 in range(n - l + 1):
                for i2 in range(n - l + 1):
                    for i in range(1, l):
                        if dp[i1][i2][i] and dp[i1 + i][i2 + i][l - i]:
                            dp[i1][i2][l] = True
                            break
                        if dp[i1][i2 + l - i][i] and dp[i1 + i][i2][l - i]:
                            dp[i1][i2][l] = True
                            break
        return dp[0][0][n]
