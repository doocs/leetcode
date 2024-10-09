class Solution:
    def numberOfSets(self, n: int, maxDistance: int, roads: List[List[int]]) -> int:
        ans = 0
        for mask in range(1 << n):
            g = [[inf] * n for _ in range(n)]
            for u, v, w in roads:
                if mask >> u & 1 and mask >> v & 1:
                    g[u][v] = min(g[u][v], w)
                    g[v][u] = min(g[v][u], w)
            for k in range(n):
                if mask >> k & 1:
                    g[k][k] = 0
                    for i in range(n):
                        for j in range(n):
                            # g[i][j] = min(g[i][j], g[i][k] + g[k][j])
                            if g[i][k] + g[k][j] < g[i][j]:
                                g[i][j] = g[i][k] + g[k][j]
            if all(
                g[i][j] <= maxDistance
                for i in range(n)
                for j in range(n)
                if mask >> i & 1 and mask >> j & 1
            ):
                ans += 1
        return ans
