class Solution:
    def numberWays(self, hats: List[List[int]]) -> int:
        g = defaultdict(list)
        for i, h in enumerate(hats):
            for v in h:
                g[v].append(i)
        mod = 10**9 + 7
        n = len(hats)
        m = max(max(h) for h in hats)
        f = [[0] * (1 << n) for _ in range(m + 1)]
        f[0][0] = 1
        for i in range(1, m + 1):
            for j in range(1 << n):
                f[i][j] = f[i - 1][j]
                for k in g[i]:
                    if j >> k & 1:
                        f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod
        return f[m][-1]
