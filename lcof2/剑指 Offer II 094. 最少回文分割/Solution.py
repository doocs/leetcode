class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        dp1 = [[False] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i, n):
                dp1[i][j] = s[i] == s[j] and (j - 1 < 3 or dp1[i + 1][j - 1])
        dp2 = [0] * n
        for i in range(n):
            if not dp1[0][i]:
                dp2[i] = i
                for j in range(1, i + 1):
                    if dp1[j][i]:
                        dp2[i] = min(dp2[i], dp2[j - 1] + 1)
        return dp2[-1]
