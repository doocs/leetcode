class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        N = 110
        INF = 0x3f3f
        g = defaultdict(list)
        for u, v, w in times:
            g[u].append((v, w))
        dist = [INF] * N
        dist[k] = 0
        q = [(0, k)]
        while q:
            t, u = heappop(q)
            if dist[u] < t:
                continue
            for v, t in g[u]:
                d = dist[u] + t
                if d < dist[v]:
                    dist[v] = d
                    heappush(q, (dist[v], v))

        ans = max(dist[1: n + 1])
        return -1 if ans == INF else ans
