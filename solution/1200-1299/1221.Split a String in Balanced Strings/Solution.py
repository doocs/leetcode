class Solution:
    def balancedStringSplit(self, s: str) -> int:
        n = res = 0
        for c in s:
            if c == 'L':
                n += 1
            else:
                n -= 1
            if n == 0:
                res += 1
        return res
