class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                            if grid[i + x][j + y] == 1:
                                p[find(i * n + j)] = find((i + x) * n + j + y)

        res = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1 and find(i * n + j) != find(m * n):
                    res += 1
        return res
