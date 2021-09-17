class Solution:
    def checkZeroOnes(self, s: str) -> bool:
        n0 = n1 = 0
        t0 = t1 = 0
        for c in s:
            if c == '0':
                t0 += 1
                t1 = 0
            else:
                t0 = 0
                t1 += 1
            n0 = max(n0, t0)
            n1 = max(n1, t1)
        return n1 > n0
