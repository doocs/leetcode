class Solution:
    def hitBricks(self, grid: List[List[int]], hits: List[List[int]]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                size[pb] += size[pa]
                p[pa] = pb

        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        size = [1] * len(p)
        g = deepcopy(grid)
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
        ans = []
        for i, j in hits[::-1]:
            if grid[i][j] == 0:
                ans.append(0)
                continue
            g[i][j] = 1
            prev = size[find(m * n)]
            if i == 0:
                union(j, m * n)
            for a, b in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and g[x][y] == 1:
                    union(i * n + j, x * n + y)
            curr = size[find(m * n)]
            ans.append(max(0, curr - prev - 1))
        return ans[::-1]
