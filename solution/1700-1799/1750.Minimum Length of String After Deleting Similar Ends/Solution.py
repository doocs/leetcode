class Solution:
    def minimumLength(self, s: str) -> int:
        i, j = 0, len(s) - 1
        while i < j and s[i] == s[j]:
            while i + 1 < j and s[i] == s[i + 1]:
                i += 1
            while i < j - 1 and s[j - 1] == s[j]:
                j -= 1
            i, j = i + 1, j - 1
        return max(0, j - i + 1)
