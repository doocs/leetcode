class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        n, j = len(t), 0
        for c in s:
            if j < n and c == t[j]:
                j += 1
        return n - j
