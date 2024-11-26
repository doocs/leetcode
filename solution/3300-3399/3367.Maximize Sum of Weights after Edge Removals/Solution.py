class Solution:
    def maximizeSumOfWeights(self, edges: List[List[int]], k: int) -> int:
        def dfs(u: int, fa: int) -> Tuple[int, int]:
            s = 0
            t = []
            for v, w in g[u]:
                if v == fa:
                    continue
                a, b = dfs(v, u)
                s += a
                if (d := (w + b - a)) > 0:
                    t.append(d)
            t.sort(reverse=True)
            return s + sum(t[:k]), s + sum(t[: k - 1])

        n = len(edges) + 1
        g: List[List[Tuple[int, int]]] = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        x, y = dfs(0, -1)
        return max(x, y)
