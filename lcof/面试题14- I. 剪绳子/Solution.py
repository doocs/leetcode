class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        s1, m = divmod(n, 3)
        if m == 1:
            s1 -= 1
            m = 4
        return pow(3, s1) * (1 if m == 0 else m)