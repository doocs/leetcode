class Solution:
    def minimumTime(
        self, n: int, edges: List[List[int]], disappear: List[int]
    ) -> List[int]:
        g = defaultdict(list)
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [inf] * n
        dist[0] = 0
        pq = [(0, 0)]
        while pq:
            du, u = heappop(pq)
            if du > dist[u]:
                continue
            for v, w in g[u]:
                if dist[v] > dist[u] + w and dist[u] + w < disappear[v]:
                    dist[v] = dist[u] + w
                    heappush(pq, (dist[v], v))
        return [a if a < b else -1 for a, b in zip(dist, disappear)]
