class Solution:
    def minPathCost(self, grid: List[List[int]], moveCost: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = grid[0]
        for i in range(1, m):
            g = [inf] * n
            for j in range(n):
                for k in range(n):
                    g[j] = min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j])
            f = g
        return min(f)
