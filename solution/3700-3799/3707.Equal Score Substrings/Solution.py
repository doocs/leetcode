class Solution:
    def scoreBalance(self, s: str) -> bool:
        l = 0
        r = sum(ord(c) - ord("a") + 1 for c in s)
        for c in s[:-1]:
            x = ord(c) - ord("a") + 1
            l += x
            r -= x
            if l == r:
                return True
        return False
