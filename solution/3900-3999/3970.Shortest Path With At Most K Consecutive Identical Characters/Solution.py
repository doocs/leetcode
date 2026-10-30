class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], labels: str, k: int) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))

        dist = [[inf] * (k + 1) for _ in range(n)]
        dist[0][1] = 0
        pq = [(0, 0, 1)]
        while pq:
            d, u, cur = heappop(pq)
            if u == n - 1:
                return d
            if d > dist[u][cur]:
                continue
            for v, w in g[u]:
                nxt = 1 if labels[v] != labels[u] else cur + 1
                nd = d + w
                if nxt <= k and nd < dist[v][nxt]:
                    dist[v][nxt] = nd
                    heappush(pq, (nd, v, nxt))
        return -1
