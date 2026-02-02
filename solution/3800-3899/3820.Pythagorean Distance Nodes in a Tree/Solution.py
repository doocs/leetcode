class Solution:
    def specialNodes(
        self, n: int, edges: List[List[int]], x: int, y: int, z: int
    ) -> int:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)

        def bfs(i: int) -> List[int]:
            q = deque([i])
            dist = [inf] * n
            dist[i] = 0
            while q:
                for _ in range(len(q)):
                    u = q.popleft()
                    for v in g[u]:
                        if dist[v] > dist[u] + 1:
                            dist[v] = dist[u] + 1
                            q.append(v)
            return dist

        d1 = bfs(x)
        d2 = bfs(y)
        d3 = bfs(z)
        ans = 0
        for a, b, c in zip(d1, d2, d3):
            s = a + b + c
            a, c = min(a, b, c), max(a, b, c)
            b = s - a - c
            if a * a + b * b == c * c:
                ans += 1
        return ans
