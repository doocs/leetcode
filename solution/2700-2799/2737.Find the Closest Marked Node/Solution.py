class Solution:
    def minimumDistance(
        self, n: int, edges: List[List[int]], s: int, marked: List[int]
    ) -> int:
        g = [[inf] * n for _ in range(n)]
        for u, v, w in edges:
            g[u][v] = min(g[u][v], w)
        dist = [inf] * n
        vis = [False] * n
        dist[s] = 0
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(n):
                dist[j] = min(dist[j], dist[t] + g[t][j])
        ans = min(dist[i] for i in marked)
        return -1 if ans >= inf else ans
