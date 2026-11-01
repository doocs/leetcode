class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        f = [[[0, 0] for _ in range(n)] for _ in range(m)]
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                r = f[i][j - 1][0] if j else 0
                d = f[i - 1][j][1] if i else 0
                if i == 0 and j == 0:
                    d = 1
                if x == 1:
                    f[i][j] = [d, r]
                else:
                    s = (r + d) % mod
                    f[i][j] = [s, s]
        return f[-1][-1][0]
