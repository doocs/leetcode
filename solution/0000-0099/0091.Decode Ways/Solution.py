class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)
        a, b, c = 0, 1, 0
        for i in range(1, n + 1):
            c = 0
            if s[i - 1] != '0':
                c += b
            if i > 1 and s[i - 2] != '0' and (int(s[i - 2]) * 10 + int(s[i - 1]) <= 26):
                c += a
            a, b = b, c
        return c
