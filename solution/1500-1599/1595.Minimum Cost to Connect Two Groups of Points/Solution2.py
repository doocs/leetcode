class Solution:
    def connectTwoGroups(self, cost: List[List[int]]) -> int:
        m, n = len(cost), len(cost[0])
        f = [inf] * (1 << n)
        f[0] = 0
        g = f[:]
        for i in range(1, m + 1):
            for j in range(1 << n):
                g[j] = inf
                for k in range(n):
                    if (j >> k & 1) == 0:
                        continue
                    c = cost[i - 1][k]
                    x = min(g[j ^ (1 << k)], f[j], f[j ^ (1 << k)]) + c
                    g[j] = min(g[j], x)
            f = g[:]
        return f[-1]
