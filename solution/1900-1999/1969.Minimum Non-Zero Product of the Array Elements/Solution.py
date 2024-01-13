class Solution:
    def minNonZeroProduct(self, p: int) -> int:
        mod = 10**9 + 7
        return (2**p - 1) * pow(2**p - 2, 2 ** (p - 1) - 1, mod) % mod
