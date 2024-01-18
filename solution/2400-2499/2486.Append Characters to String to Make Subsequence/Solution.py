class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        i, m = 0, len(s)
        for j, c in enumerate(t):
            while i < m and s[i] != c:
                i += 1
            if i == m:
                return len(t) - j
            i += 1
        return 0
