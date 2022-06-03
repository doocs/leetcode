class Solution:
    def minimumWeight(
        self, n: int, edges: List[List[int]], src1: int, src2: int, dest: int
    ) -> int:
        def dijkstra(g, u):
            dist = [inf] * n
            dist[u] = 0
            q = [(0, u)]
            while q:
                d, u = heappop(q)
                if d > dist[u]:
                    continue
                for v, w in g[u]:
                    if dist[v] > dist[u] + w:
                        dist[v] = dist[u] + w
                        heappush(q, (dist[v], v))
            return dist

        g = defaultdict(list)
        rg = defaultdict(list)
        for f, t, w in edges:
            g[f].append((t, w))
            rg[t].append((f, w))
        d1 = dijkstra(g, src1)
        d2 = dijkstra(g, src2)
        d3 = dijkstra(rg, dest)
        ans = min(sum(v) for v in zip(d1, d2, d3))
        return -1 if ans >= inf else ans
