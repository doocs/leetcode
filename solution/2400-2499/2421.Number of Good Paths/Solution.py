class Solution:
    def numberOfGoodPaths(self, vals: List[int], edges: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        n = len(vals)
        p = list(range(n))
        size = defaultdict(Counter)
        for i, v in enumerate(vals):
            size[i][v] = 1

        ans = n
        for v, a in sorted(zip(vals, range(n))):
            for b in g[a]:
                if vals[b] > v:
                    continue
                pa, pb = find(a), find(b)
                if pa != pb:
                    ans += size[pa][v] * size[pb][v]
                    p[pa] = pb
                    size[pb][v] += size[pa][v]
        return ans
