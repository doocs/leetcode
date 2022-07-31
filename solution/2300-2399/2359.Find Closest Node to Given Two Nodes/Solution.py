class Solution:
    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        def dijkstra(g, u):
            dist = [inf] * n
            dist[u] = 0
            q = [(0, u)]
            while q:
                d, u = heappop(q)
                if d > dist[u]:
                    continue
                for v in g[u]:
                    if dist[v] > dist[u] + 1:
                        dist[v] = dist[u] + 1
                        heappush(q, (dist[v], v))
            return dist

        g = defaultdict(list)
        n = len(edges)
        for i, v in enumerate(edges):
            if v != -1:
                g[i].append(v)
        d1 = dijkstra(g, node1)
        d2 = dijkstra(g, node2)
        d = inf
        ans = -1
        for i, (a, b) in enumerate(zip(d1, d2)):
            if d > max(a, b):
                d = max(a, b)
                ans = i
        return ans
