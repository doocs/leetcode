class Solution:
    def findSpecialNodes(self, n: int, edges: List[List[int]]) -> str:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        def bfs(start: int):
            dist = [-1] * n
            dist[start] = 0
            q = deque([start])
            far = start
            while q:
                u = q.popleft()
                if dist[u] > dist[far]:
                    far = u
                for v in g[u]:
                    if dist[v] == -1:
                        dist[v] = dist[u] + 1
                        q.append(v)
            return far, dist

        a, _ = bfs(0)
        b, dist1 = bfs(a)
        _, dist2 = bfs(b)
        d = dist1[b]
        ans = ["0"] * n
        for i in range(n):
            if dist1[i] == d or dist2[i] == d:
                ans[i] = "1"
        return "".join(ans)
