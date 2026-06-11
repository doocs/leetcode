class Solution:
    def assignEdgeWeights(self, edges: List[List[int]]) -> int:
        def dfs(i: int, fa: int = 0) -> int:
            res = 0
            for j in g[i]:
                if j != fa:
                    res = max(res, dfs(j, i) + 1)
            return res

        n = len(edges) + 1
        g = [[] for _ in range(n + 1)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        d = dfs(1)
        return pow(2, d - 1, 10**9 + 7)
