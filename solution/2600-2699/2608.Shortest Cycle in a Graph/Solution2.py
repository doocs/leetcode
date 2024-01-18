class Solution:
    def findShortestCycle(self, n: int, edges: List[List[int]]) -> int:
        def bfs(u: int) -> int:
            dist = [-1] * n
            dist[u] = 0
            q = deque([(u, -1)])
            ans = inf
            while q:
                u, fa = q.popleft()
                for v in g[u]:
                    if dist[v] < 0:
                        dist[v] = dist[u] + 1
                        q.append((v, u))
                    elif v != fa:
                        ans = min(ans, dist[u] + dist[v] + 1)
            return ans

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        ans = min(bfs(i) for i in range(n))
        return ans if ans < inf else -1
