class Solution:
    def numberOfPaths(self, grid: List[List[int]], K: int) -> int:
        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        f = [[[0] * K for _ in range(n)] for _ in range(m)]
        f[0][0][grid[0][0] % K] = 1
        for i in range(m):
            for j in range(n):
                for k in range(K):
                    k0 = ((k - grid[i][j] % K) + K) % K
                    if i:
                        f[i][j][k] += f[i - 1][j][k0]
                    if j:
                        f[i][j][k] += f[i][j - 1][k0]
                    f[i][j][k] %= mod
        return f[m - 1][n - 1][0]
