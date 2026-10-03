class Solution:
    def maximizeSumOfWeights(self, edges: List[List[int]], k: int) -> int:
        n = len(edges) + 1
        g: List[List[Tuple[int, int]]] = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        keep = [0] * n
        reserve = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            u, fa, state = stk.pop()
            if state == 0:
                stk.append((u, fa, 1))
                for v, _ in g[u]:
                    if v != fa:
                        stk.append((v, u, 0))
            else:
                s = 0
                t = []
                for v, w in g[u]:
                    if v == fa:
                        continue
                    a, b = keep[v], reserve[v]
                    s += a
                    if (d := (w + b - a)) > 0:
                        t.append(d)
                t.sort(reverse=True)
                keep[u] = s + sum(t[:k])
                reserve[u] = s + sum(t[: k - 1])
        return max(keep[0], reserve[0])
