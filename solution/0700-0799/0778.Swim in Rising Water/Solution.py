class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(grid)
        p = list(range(n * n))
        hi = [0] * (n * n)
        for i, row in enumerate(grid):
            for j, h in enumerate(row):
                hi[h] = i * n + j
        for t in range(n * n):
            i, j = hi[t] // n, hi[t] % n
            for a, b in [(0, -1), (0, 1), (1, 0), (-1, 0)]:
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] <= t:
                    p[find(x * n + y)] = find(hi[t])
                if find(0) == find(n * n - 1):
                    return t
        return -1
