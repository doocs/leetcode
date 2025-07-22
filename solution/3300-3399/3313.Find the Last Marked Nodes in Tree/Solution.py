class Solution:
    def lastMarkedNodes(self, edges: List[List[int]]) -> List[int]:
        def dfs(i: int, fa: int, dist: List[int]):
            for j in g[i]:
                if j != fa:
                    dist[j] = dist[i] + 1
                    dfs(j, i, dist)

        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)

        dist1 = [-1] * n
        dist1[0] = 0
        dfs(0, -1, dist1)
        a = dist1.index(max(dist1))

        dist2 = [-1] * n
        dist2[a] = 0
        dfs(a, -1, dist2)
        b = dist2.index(max(dist2))

        dist3 = [-1] * n
        dist3[b] = 0
        dfs(b, -1, dist3)

        return [a if x > y else b for x, y in zip(dist2, dist3)]
