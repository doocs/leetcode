class Solution:
    def pondSizes(self, land: List[List[int]]) -> List[int]:
        m, n = len(land), len(land[0])
        p = list(range(m * n))
        size = [1] * (m * n)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            size[pb] += size[pa]
            p[pa] = pb

        for i in range(m):
            for j in range(n):
                if land[i][j] != 0:
                    continue
                idx = i * n + j
                if i < m - 1 and land[i + 1][j] == 0:
                    union(idx, (i + 1) * n + j)
                if j < n - 1 and land[i][j + 1] == 0:
                    union(idx, i * n + j + 1)
                if i < m - 1 and j < n - 1 and land[i + 1][j + 1] == 0:
                    union(idx, (i + 1) * n + j + 1)
                if i < m - 1 and j > 0 and land[i + 1][j - 1] == 0:
                    union(idx, (i + 1) * n + j - 1)

        s = set()
        res = []
        for i in range(m * n):
            if land[i // n][i % n] != 0:
                continue
            root = find(i)
            if root not in s:
                s.add(root)
                res.append(size[root])
        res.sort()
        return res
