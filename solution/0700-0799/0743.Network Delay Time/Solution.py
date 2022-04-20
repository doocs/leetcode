class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        N = 110
        INF = 0x3f3f
        g = [[INF] * N for _ in range(N)]
        for u, v, w in times:
            g[u][v] = w
        dist = [INF] * N
        dist[k] = 0
        vis = [False] * N
        for i in range(n):
            t = -1
            for j in range(1, n + 1):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(1, n + 1):
                dist[j] = min(dist[j], dist[t] + g[t][j])

        ans = max(dist[1: n + 1])
        return -1 if ans == INF else ans
