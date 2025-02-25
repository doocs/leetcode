class Solution:
    def checkPartitioning(self, s: str) -> bool:
        n = len(s)
        f = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = s[i] == s[j] and (i + 1 == j or f[i + 1][j - 1])
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                if f[0][i] and f[i + 1][j] and f[j + 1][-1]:
                    return True
        return False
