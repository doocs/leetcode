class Solution:
    def findShortestCycle(self, n: int, edges: List[List[int]]) -> int:
        def bfs(u: int, v: int) -> int:
            dist = [inf] * n
            dist[u] = 0
            q = deque([u])
            while q:
                i = q.popleft()
                for j in g[i]:
                    if (i, j) != (u, v) and (j, i) != (u, v) and dist[j] == inf:
                        dist[j] = dist[i] + 1
                        q.append(j)
            return dist[v] + 1

        g = defaultdict(set)
        for u, v in edges:
            g[u].add(v)
            g[v].add(u)
        ans = min(bfs(u, v) for u, v in edges)
        return ans if ans < inf else -1
