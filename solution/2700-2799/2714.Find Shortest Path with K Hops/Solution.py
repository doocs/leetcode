class Solution:
    def shortestPathWithHops(
        self, n: int, edges: List[List[int]], s: int, d: int, k: int
    ) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [[inf] * (k + 1) for _ in range(n)]
        dist[s][0] = 0
        pq = [(0, s, 0)]
        while pq:
            dis, u, t = heappop(pq)
            for v, w in g[u]:
                if t + 1 <= k and dist[v][t + 1] > dis:
                    dist[v][t + 1] = dis
                    heappush(pq, (dis, v, t + 1))
                if dist[v][t] > dis + w:
                    dist[v][t] = dis + w
                    heappush(pq, (dis + w, v, t))
        return int(min(dist[d]))
