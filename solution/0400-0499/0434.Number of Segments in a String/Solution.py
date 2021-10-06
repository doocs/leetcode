class Solution:
    def countSegments(self, s: str) -> int:
        res, n = 0, len(s)
        for i in range(n):
            if s[i] != ' ' and (i == 0 or s[i - 1] == ' '):
                res += 1
        return res
