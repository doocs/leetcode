class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        k %= m * n
        t = [grid[i][j] for i in range(m) for j in range(n)]
        t = t[-k:] + t[:-k]
        for i in range(m):
            for j in range(n):
                grid[i][j] = t[i * n + j]
        return grid
