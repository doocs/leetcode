class Solution:
    def minCost(
        self, n: int, roads: List[List[int]], appleCost: List[int], k: int
    ) -> List[int]:
        def dijkstra(i):
            q = [(0, i)]
            dist = [inf] * n
            dist[i] = 0
            ans = inf
            while q:
                d, u = heappop(q)
                ans = min(ans, appleCost[u] + d * (k + 1))
                for v, w in g[u]:
                    if dist[v] > dist[u] + w:
                        dist[v] = dist[u] + w
                        heappush(q, (dist[v], v))
            return ans

        g = defaultdict(list)
        for a, b, c in roads:
            a, b = a - 1, b - 1
            g[a].append((b, c))
            g[b].append((a, c))
        return [dijkstra(i) for i in range(n)]
