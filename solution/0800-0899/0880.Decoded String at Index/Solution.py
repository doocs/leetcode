class Solution:
    def decodeAtIndex(self, s: str, k: int) -> str:
        m = 0
        for c in s:
            if c.isdigit():
                m *= int(c)
            else:
                m += 1
        for c in s[::-1]:
            k %= m
            if k == 0 and c.isalpha():
                return c
            if c.isdigit():
                m //= int(c)
            else:
                m -= 1
