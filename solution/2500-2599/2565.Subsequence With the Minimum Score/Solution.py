class Solution:
    def minimumScore(self, s: str, t: str) -> int:
        def check(x):
            for k in range(n):
                i, j = k - 1, k + x
                l = f[i] if i >= 0 else -1
                r = g[j] if j < n else m + 1
                if l < r:
                    return True
            return False

        m, n = len(s), len(t)
        f = [inf] * n
        g = [-1] * n
        i, j = 0, 0
        while i < m and j < n:
            if s[i] == t[j]:
                f[j] = i
                j += 1
            i += 1
        i, j = m - 1, n - 1
        while i >= 0 and j >= 0:
            if s[i] == t[j]:
                g[j] = i
                j -= 1
            i -= 1

        return bisect_left(range(n + 1), True, key=check)
