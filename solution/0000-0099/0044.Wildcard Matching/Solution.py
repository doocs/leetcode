class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        f = [False] * (n + 1)
        f[0] = True
        for j in range(1, n + 1):
            if p[j - 1] == "*":
                f[j] = f[j - 1]
        for i in range(1, m + 1):
            g = [False] * (n + 1)
            for j in range(1, n + 1):
                if p[j - 1] == "*":
                    g[j] = g[j - 1] or f[j] or f[j - 1]
                else:
                    g[j] = f[j - 1] and (
                        p[j - 1] == "?" or s[i - 1] == p[j - 1]
                    )
            f = g
        return f[n]
