class Solution:
    def isUnique(self, astr: str) -> bool:
        mask = 0
        for c in astr:
            i = ord(c) - ord('a')
            if (mask >> i) & 1:
                return False
            mask |= 1 << i
        return True
