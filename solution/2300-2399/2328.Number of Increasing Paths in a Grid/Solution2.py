class Solution:
    def countPaths(self, grid: List[List[int]]) -> int:
        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        f = [[1] * n for _ in range(m)]
        cells = [(grid[i][j], i, j) for i in range(m) for j in range(n)]
        cells.sort(reverse=True)
        dirs = (-1, 0, 1, 0, -1)
        for _, i, j in cells:
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[i][j] < grid[x][y]:
                    f[i][j] = (f[i][j] + f[x][y]) % mod
        return sum(sum(row) for row in f) % mod
