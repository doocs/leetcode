class Solution:
    def hasValidPath(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def left(i, j):
            if j > 0 and grid[i][j - 1] in (1, 4, 6):
                p[find(i * n + j)] = find(i * n + j - 1)

        def right(i, j):
            if j < n - 1 and grid[i][j + 1] in (1, 3, 5):
                p[find(i * n + j)] = find(i * n + j + 1)

        def up(i, j):
            if i > 0 and grid[i - 1][j] in (2, 3, 4):
                p[find(i * n + j)] = find((i - 1) * n + j)

        def down(i, j):
            if i < m - 1 and grid[i + 1][j] in (2, 5, 6):
                p[find(i * n + j)] = find((i + 1) * n + j)

        for i in range(m):
            for j in range(n):
                e = grid[i][j]
                if e == 1:
                    left(i, j)
                    right(i, j)
                elif e == 2:
                    up(i, j)
                    down(i, j)
                elif e == 3:
                    left(i, j)
                    down(i, j)
                elif e == 4:
                    right(i, j)
                    down(i, j)
                elif e == 5:
                    left(i, j)
                    up(i, j)
                else:
                    right(i, j)
                    up(i, j)
        return find(0) == find(m * n - 1)
