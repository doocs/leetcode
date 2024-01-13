class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            res = int(0 < i < m - 1 and 0 < j < n - 1)
            grid[i][j] = 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 0:
                    res &= dfs(x, y)
            return res

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(grid[i][j] == 0 and dfs(i, j) for i in range(m) for j in range(n))
