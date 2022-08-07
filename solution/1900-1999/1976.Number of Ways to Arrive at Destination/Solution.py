class Solution:
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        INF = inf
        MOD = 10**9 + 7
        g = [[INF] * n for _ in range(n)]
        for u, v, t in roads:
            g[u][v] = t
            g[v][u] = t
        g[0][0] = 0
        dist = [INF] * n
        w = [0] * n
        dist[0] = 0
        w[0] = 1
        vis = [False] * n
        for _ in range(n):
            t = -1
            for i in range(n):
                if not vis[i] and (t == -1 or dist[i] < dist[t]):
                    t = i
            vis[t] = True
            for i in range(n):
                if i == t:
                    continue
                ne = dist[t] + g[t][i]
                if dist[i] > ne:
                    dist[i] = ne
                    w[i] = w[t]
                elif dist[i] == ne:
                    w[i] += w[t]
        return w[-1] % MOD
