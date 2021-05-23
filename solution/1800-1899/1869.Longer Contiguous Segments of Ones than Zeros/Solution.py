class Solution:
    def checkZeroOnes(self, s: str) -> bool:
        len0 = len1 = 0
        t0 = t1 = 0
        for c in s:
            if c == '0':
                t0 += 1
                t1 = 0
            else:
                t0 = 0
                t1 += 1
            len0 = max(len0, t0)
            len1 = max(len1, t1)
        return len1 > len0
