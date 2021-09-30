class Solution:
    def calcEquation(self, equations: List[List[str]], values: List[float], queries: List[List[str]]) -> List[float]:
        w = collections.defaultdict(lambda: 1)
        p = collections.defaultdict()
        for a, b in equations:
            p[a] = a
            p[b] = b

        def find(x):
            if p[x] != x:
                origin = p[x]
                p[x] = find(p[x])
                w[x] *= w[origin]
            return p[x]

        for i, e in enumerate(equations):
            pa, pb = find(e[0]), find(e[1])
            if pa == pb:
                continue
            p[pa] = pb
            w[pa] = w[e[1]] * values[i] / w[e[0]]

        return [-1 if c not in p or d not in p or find(c) != find(d) else w[c] / w[d] for c, d in queries]
