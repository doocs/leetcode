class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        i = 0
        for j in range(n):
            while i < m and s[i] != t[j]:
                i += 1
            if i == m:
                return n - j
            i += 1
        return 0
