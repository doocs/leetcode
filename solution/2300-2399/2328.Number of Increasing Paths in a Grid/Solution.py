class Solution:
    def countPaths(self, grid: List[List[int]]) -> int:
        @cache
        def dfs(i, j):
            res = 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] > grid[i][j]:
                    res += dfs(x, y)
            return res

        m, n = len(grid), len(grid[0])
        mod = 10**9 + 7
        return sum(dfs(i, j) for i in range(m) for j in range(n)) % mod
