class Solution:
    def cuttingRope(self, n: int) -> int:
        if n < 4:
            return n - 1
        res = 1
        while n > 4:
            res *= 3
            n -= 3
        if n == 4:
            return (res << 2) % 1000000007
        return (res * n) % 1000000007
