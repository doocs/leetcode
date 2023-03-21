class Solution:
    def findTheCity(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        def dijkstra(u):
            dist = [inf] * n
            dist[u] = 0
            vis = [False] * n
            for _ in range(n):
                k = -1
                for j in range(n):
                    if not vis[j] and (k == -1 or dist[k] > dist[j]):
                        k = j
                vis[k] = True
                for j in range(n):
                    dist[j] = min(dist[j], dist[k] + g[k][j])
            return sum(d <= distanceThreshold for d in dist)

        g = [[inf] * n for _ in range(n)]
        for f, t, w in edges:
            g[f][t] = g[t][f] = w

        ans = n
        t = inf
        for i in range(n - 1, -1, -1):
            if (cnt := dijkstra(i)) < t:
                t = cnt
                ans = i
        return ans
