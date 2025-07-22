class Solution:
    def knightDialer(self, n: int) -> int:
        f = [1] * 10
        for _ in range(n - 1):
            g = [0] * 10
            g[0] = f[4] + f[6]
            g[1] = f[6] + f[8]
            g[2] = f[7] + f[9]
            g[3] = f[4] + f[8]
            g[4] = f[0] + f[3] + f[9]
            g[6] = f[0] + f[1] + f[7]
            g[7] = f[2] + f[6]
            g[8] = f[1] + f[3]
            g[9] = f[2] + f[4]
            f = g
        return sum(f) % (10**9 + 7)
