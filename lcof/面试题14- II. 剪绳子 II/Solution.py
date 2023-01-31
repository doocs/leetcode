class Solution:
    def cuttingRope(self, n: int) -> int:
        mod = 10**9 + 7
        if n < 4:
            return n - 1
        if n % 3 == 0:
            return pow(3, n // 3, mod)
        if n % 3 == 1:
            return (pow(3, n // 3 - 1, mod) * 4) % mod
        return pow(3, n // 3, mod) * 2 % mod
