class Solution:
    def isUnique(self, astr: str) -> bool:
        bitmap = 0
        for c in astr:
            pos = ord(c) - ord('a')
            if (bitmap & (1 << pos)) != 0:
                return False
            bitmap |= 1 << pos
        return True
