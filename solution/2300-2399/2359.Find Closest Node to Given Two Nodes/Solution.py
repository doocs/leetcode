class Solution:
    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        def dijkstra(i):
            dist = [inf] * n
            dist[i] = 0
            q = [(0, i)]
            while q:
                i = heappop(q)[1]
                for j in g[i]:
                    if dist[j] > dist[i] + 1:
                        dist[j] = dist[i] + 1
                        heappush(q, (dist[j], j))
            return dist

        g = defaultdict(list)
        for i, j in enumerate(edges):
            if j != -1:
                g[i].append(j)
        n = len(edges)
        d1 = dijkstra(node1)
        d2 = dijkstra(node2)
        ans, d = -1, inf
        for i, (a, b) in enumerate(zip(d1, d2)):
            if (t := max(a, b)) < d:
                d = t
                ans = i
        return ans
