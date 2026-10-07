class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def flood(i: int, j: int):
            stk = [(i, j)]
            grid[i][j] = 0
            while stk:
                i, j = stk.pop()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y]:
                        grid[x][y] = 0
                        stk.append((x, y))

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        for j in range(n):
            for i in (0, m - 1):
                if grid[i][j]:
                    flood(i, j)
        for i in range(m):
            for j in (0, n - 1):
                if grid[i][j]:
                    flood(i, j)
        return sum(sum(row) for row in grid)
