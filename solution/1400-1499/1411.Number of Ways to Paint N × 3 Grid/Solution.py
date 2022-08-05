class Solution:
    def numOfWays(self, n: int) -> int:
        mod = 10**9 + 7
        f0 = f1 = 6
        for _ in range(n - 1):
            g0 = (3 * f0 + 2 * f1) % mod
            g1 = (2 * f0 + 2 * f1) % mod
            f0, f1 = g0, g1
        return (f0 + f1) % mod
