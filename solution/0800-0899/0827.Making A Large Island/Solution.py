class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        n = len(grid)
        p = list(range(n * n))
        size = [1] * (n * n)

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
            return 0 <= i < n and 0 <= j < n and grid[i][j] == 1

        for i in range(n):
            for j in range(n):
                if grid[i][j] == 1:
                    for x, y in [[1, 0], [0, 1]]:
                        if check(i + x, j + y):
                            union(i * n + j, (i + x) * n + j + y)

        res = max(size)
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 0:
                    t = 1
                    s = set()
                    for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                        if check(i + x, j + y):
                            root = find((i + x) * n + j + y)
                            if root not in s:
                                t += size[root]
                                s.add(root)
                    res = max(res, t)
        return res
