class Solution:
    def assignEdgeWeights(self, edges: List[List[int]]) -> int:
        n = len(edges) + 1
        g = [[] for _ in range(n + 1)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        stk = [(1, 0, 0)]
        d = 0
        while stk:
            i, fa, dep = stk.pop()
            d = max(d, dep)
            for j in g[i]:
                if j != fa:
                    stk.append((j, i, dep + 1))
        return pow(2, d - 1, 10**9 + 7)
