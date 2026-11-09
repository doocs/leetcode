class Solution:
    def minTimeMaxPower(
        self,
        n: int,
        edges: list[list[int]],
        power: int,
        cost: list[int],
        source: int,
        target: int,
    ) -> list[int]:
        g = [[] for _ in range(n)]
        for u, v, t in edges:
            g[u].append((v, t))
        dist = [[inf] * (power + 1) for _ in range(n)]
        dist[source][power] = 0
        pq = [(0, -power, source)]
        while pq:
            d, p, u = heappop(pq)
            p = -p
            if u == target:
                return [d, p]
            if d > dist[u][p] or p < cost[u]:
                continue
            p -= cost[u]
            for v, t in g[u]:
                nd = d + t
                if nd < dist[v][p]:
                    dist[v][p] = nd
                    heappush(pq, (nd, -p, v))
        return [-1, -1]
