class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in times:
            g[u - 1].append((v - 1, w))
        dist = [inf] * n
        dist[k - 1] = 0
        pq = [(0, k - 1)]
        while pq:
            d, u = heappop(pq)
            if d > dist[u]:
                continue
            for v, w in g[u]:
                if (nd := d + w) < dist[v]:
                    dist[v] = nd
                    heappush(pq, (nd, v))
        ans = max(dist)
        return -1 if ans == inf else ans
