class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        res = 0
        for i in range(26):
            c = chr(ord('a') + i)
            if c in s:
                l, r = s.index(c), s.rindex(c)
                chars = {s[j] for j in range(l + 1, r)}
                res += len(chars)
        return res
