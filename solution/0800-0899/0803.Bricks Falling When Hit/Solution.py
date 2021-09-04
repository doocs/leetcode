class Solution:
    def hitBricks(self, grid: List[List[int]], hits: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        size = [1] * len(p)
        g = [[grid[i][j] for j in range(n)] for i in range(m)]

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                size[pb] += size[pa]
                p[pa] = pb

        def check(i, j):
            return 0 <= i < m and 0 <= j < n and g[i][j] == 1

        for i, j in hits:
            g[i][j] = 0

        for j in range(n):
            if g[0][j] == 1:
                union(j, m * n)

        for i in range(1, m):
            for j in range(n):
                if g[i][j] == 0:
                    continue
                if g[i - 1][j] == 1:
                    union(i * n + j, (i - 1) * n + j)
                if j > 0 and g[i][j - 1] == 1:
                    union(i * n + j, i * n + j - 1)

        res = []
        for i, j in hits[::-1]:
            if grid[i][j] == 0:
                res.append(0)
                continue
            origin = size[find(m * n)]
            if i == 0:
                union(j, m * n)
            for x, y in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
                if check(i + x, j + y):
                    union(i * n + j, (i + x) * n + (j + y))
            cur = size[find(m * n)]
            res.append(max(0, cur - origin - 1))
            g[i][j] = 1
        return res[::-1]
