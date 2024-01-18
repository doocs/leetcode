class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)

        def f(state):
            return sum(((state >> i) & 1) == 0 for i in range(n))

        q = []
        dist = [[inf] * (1 << n) for _ in range(n)]
        for i in range(n):
            heappush(q, (f(1 << i), i, 1 << i))
            dist[i][1 << i] = 0
        while q:
            _, u, state = heappop(q)
            if state == (1 << n) - 1:
                return dist[u][state]
            for v in graph[u]:
                nxt = state | (1 << v)
                if dist[v][nxt] > dist[u][state] + 1:
                    dist[v][nxt] = dist[u][state] + 1
                    heappush(q, (dist[v][nxt] + f(nxt), v, nxt))
        return 0
