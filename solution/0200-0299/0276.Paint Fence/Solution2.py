class Solution:
    def numWays(self, n: int, k: int) -> int:
        f, g = k, 0
        for _ in range(n - 1):
            ff = (f + g) * (k - 1)
            g = f
            f = ff
        return f + g
