class Solution:
    def largestArea(self, grid: List[str]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        size = [1] * (m * n + 1)
        dirs = [[0, -1], [0, 1], [1, 0], [-1, 0]]
        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1 or grid[i][j] == '0':
                    p[find(i * n + j)] = find(m * n)
                else:
                    for a, b in dirs:
                        x, y = i + a, j + b
                        if (grid[x][y] == '0' or grid[i][j] == grid[x][y]) and find(
                            x * n + y
                        ) != find(i * n + j):
                            size[find(x * n + y)] += size[find(i * n + j)]
                            p[find(i * n + j)] = find(x * n + y)
        return max(
            [
                size[i * n + j]
                for i in range(m)
                for j in range(n)
                if find(i * n + j) != find(m * n)
            ],
            default=0,
        )
