class Solution:
    def makeSmallestPalindrome(self, s: str) -> str:
        i, j = 0, len(s) - 1
        cs = list(s)
        while i < j:
            if s[i] != s[j]:
                cs[i] = cs[j] = min(s[i], s[j])
            i, j = i + 1, j - 1
        return "".join(cs)
