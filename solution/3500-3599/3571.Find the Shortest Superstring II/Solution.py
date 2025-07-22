class Solution:
    def shortestSuperstring(self, s1: str, s2: str) -> str:
        m, n = len(s1), len(s2)
        if m > n:
            return self.shortestSuperstring(s2, s1)
        if s1 in s2:
            return s2
        for i in range(m):
            if s2.startswith(s1[i:]):
                return s1[:i] + s2
            if s2.endswith(s1[: m - i]):
                return s2 + s1[m - i :]
        return s1 + s2
