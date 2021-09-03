class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        n = len(grid)
        p = list(range(n * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def index(i, j):
            return i * n + j

        def check(i, j):
            return 0 <= i < n and 0 <= j < n

        hi = [0] * (n * n)
        for i in range(n):
            for j in range(n):
                hi[grid[i][j]] = index(i, j)
        for h in range(n * n):
            x, y = hi[h] // n, hi[h] % n
            for a, b in [(0, -1), (0, 1), (1, 0), (-1, 0)]:
                x1, y1 = x + a, y + b
                if check(x1, y1) and grid[x1][y1] <= h:
                    p[find(index(x1, y1))] = find(hi[h])
                if find(0) == find(n * n - 1):
                    return h
        return -1
