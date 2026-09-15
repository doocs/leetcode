class Solution:
    def maxPalindromes(self, s: str, k: int) -> int:
        n = len(s)
        g = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = s[i] == s[j] and g[i + 1][j - 1]
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            f[i] = f[i + 1]
            for j in range(i + k - 1, n):
                if g[i][j]:
                    f[i] = max(f[i], 1 + f[j + 1])
        return f[0]
