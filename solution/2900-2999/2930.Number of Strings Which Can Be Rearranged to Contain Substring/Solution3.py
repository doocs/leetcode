class Solution:
    def stringCount(self, n: int) -> int:
        mod = 10**9 + 7
        f = [[[0] * 2 for _ in range(3)] for _ in range(2)]
        f[1][2][1] = 1
        for _ in range(n):
            g = [[[0] * 2 for _ in range(3)] for _ in range(2)]
            for l in range(2):
                for e in range(3):
                    for t in range(2):
                        a = f[l][e][t] * 23
                        b = f[min(1, l + 1)][e][t]
                        c = f[l][min(2, e + 1)][t]
                        d = f[l][e][min(1, t + 1)]
                        g[l][e][t] = (a + b + c + d) % mod
            f = g
        return f[0][0][0]
