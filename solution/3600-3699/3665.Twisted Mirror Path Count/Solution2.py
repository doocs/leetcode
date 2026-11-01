class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        mod = 10**9 + 7
        n = len(grid[0])
        f = [[0, 0] for _ in range(n + 1)]
        f[1] = [1, 1]
        for row in grid:
            for j, x in enumerate(row):
                r, d = f[j][0], f[j + 1][1]
                if x == 1:
                    f[j + 1] = [d, r]
                else:
                    s = (r + d) % mod
                    f[j + 1] = [s, s]
        return f[n][0]
