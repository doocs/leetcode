class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3F3F
        dist = [INF] * n
        vis = [False] * n
        g = [[INF] * n for _ in range(n)]
        for u, v, w in times:
            g[u - 1][v - 1] = w
        dist[k - 1] = 0
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(n):
                dist[j] = min(dist[j], dist[t] + g[t][j])
        ans = max(dist)
        return -1 if ans == INF else ans
