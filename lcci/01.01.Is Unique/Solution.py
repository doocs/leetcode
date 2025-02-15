class Solution:
    def isUnique(self, astr: str) -> bool:
        mask = 0
        for i in map(lambda c: ord(c) - ord("a"), astr):
            if (mask >> i) & 1:
                return False
            mask |= 1 << i
        return True
