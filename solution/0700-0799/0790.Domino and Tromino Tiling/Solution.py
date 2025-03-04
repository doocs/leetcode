class Solution:
    def numTilings(self, n: int) -> int:
        f = [1, 0, 0, 0]
        mod = 10**9 + 7
        for i in range(1, n + 1):
            g = [0] * 4
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod
            g[1] = (f[2] + f[3]) % mod
            g[2] = (f[1] + f[3]) % mod
            g[3] = f[0]
            f = g
        return f[0]
