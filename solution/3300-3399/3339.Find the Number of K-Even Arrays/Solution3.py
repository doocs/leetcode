class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        f = [[0] * 2 for _ in range(k + 1)]
        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        f[0][1] = 1
        for _ in range(n):
            g = [[0] * 2 for _ in range(k + 1)]
            for j in range(k + 1):
                g[j][0] = (f[j][1] + (f[j - 1][0] if j else 0)) * cnt0 % mod
                g[j][1] = (f[j][0] + f[j][1]) * cnt1 % mod
            f = g
        return sum(f[k]) % mod
