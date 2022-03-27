class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)
        dst = -1 ^ (-1 << n)

        q = deque()
        vis = [[False] * (1 << n) for _ in range(n)]
        for i in range(n):
            q.append((i, 1 << i, 0))
            vis[i][1 << i] = True

        while q:
            u, state, dis = q.popleft()
            for v in graph[u]:
                nxt = state | (1 << v)
                if nxt == dst:
                    return dis + 1
                if not vis[v][nxt]:
                    q.append((v, nxt, dis + 1))
                    vis[v][nxt] = True
        return 0
