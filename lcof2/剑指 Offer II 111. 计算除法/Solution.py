class Solution:
    def calcEquation(
        self, equations: List[List[str]], values: List[float], queries: List[List[str]]
    ) -> List[float]:
        n = len(equations)
        p = list(range(n << 1))
        w = [1.0] * (n << 1)

        def find(x):
            if p[x] != x:
                origin = p[x]
                p[x] = find(p[x])
                w[x] *= w[origin]
            return p[x]

        mp = {}
        idx = 0
        for i, e in enumerate(equations):
            a, b = e[0], e[1]
            if a not in mp:
                mp[a] = idx
                idx += 1
            if b not in mp:
                mp[b] = idx
                idx += 1
            pa, pb = find(mp[a]), find(mp[b])
            if pa == pb:
                continue
            p[pa] = pb
            w[pa] = w[mp[b]] * values[i] / w[mp[a]]

        res = []
        for q in queries:
            c, d = q[0], q[1]
            if c not in mp or d not in mp:
                res.append(-1.0)
            else:
                pa, pb = find(mp[c]), find(mp[d])
                res.append(w[mp[c]] / w[mp[d]] if pa == pb else -1.0)
        return res
