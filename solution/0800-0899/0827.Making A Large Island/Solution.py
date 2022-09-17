class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        n = len(grid)
        p = list(range(n * n))
        size = [1] * (n * n)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    for a, b in [[0, -1], [-1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n and grid[x][y]:
                            union(x * n + y, i * n + j)
        ans = max(size)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    vis = set()
                    t = 1
                    for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n and grid[x][y]:
                            root = find(x * n + y)
                            if root not in vis:
                                vis.add(root)
                                t += size[root]
                    ans = max(ans, t)
        return ans
