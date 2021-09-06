class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    continue
                idx = i * n + j
                if i < m - 1 and grid[i + 1][j] == 0:
                    p[find(idx)] = find((i + 1) * n + j)
                if j < n - 1 and grid[i][j + 1] == 0:
                    p[find(idx)] = find(i * n + j + 1)

        s = [0] * (m * n)
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    s[find(i * n + j)] = 1
        for i in range(m):
            for j in range(n):
                root = find(i * n + j)
                if not s[root]:
                    continue
                if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                    s[root] = 0
        return sum(s)
