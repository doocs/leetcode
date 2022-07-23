class Solution:
    def makePalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        t = 0
        while i < j:
            if s[i] != s[j]:
                t += 1
            i, j = i + 1, j - 1
        return t <= 2
