class Solution:
    def minPathCost(self, grid: List[List[int]], moveCost: List[List[int]]) -> int:
        n = len(grid[0])
        f = [0] * n
        for i, row in enumerate(grid):
            g = [0] * n
            for j, v in enumerate(row):
                g[j] = v
                t = inf
                if i:
                    for k, x in enumerate(grid[i - 1]):
                        t = min(t, f[k] + moveCost[x][j])
                if t != inf:
                    g[j] += t
            f = g
        return min(f)
