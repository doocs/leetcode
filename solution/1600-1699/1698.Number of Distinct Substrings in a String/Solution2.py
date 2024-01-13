class Solution:
    def countDistinct(self, s: str) -> int:
        base = 131
        n = len(s)
        p = [0] * (n + 10)
        h = [0] * (n + 10)
        p[0] = 1
        for i, c in enumerate(s):
            p[i + 1] = p[i] * base
            h[i + 1] = h[i] * base + ord(c)
        ss = set()
        for i in range(1, n + 1):
            for j in range(i, n + 1):
                t = h[j] - h[i - 1] * p[j - i + 1]
                ss.add(t)
        return len(ss)
