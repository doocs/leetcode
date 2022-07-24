class Solution:
    def equalPairs(self, grid: List[List[int]]) -> int:
        n = len(grid)
        g = []
        for j in range(n):
            t = []
            for i in range(n):
                t.append(grid[i][j])
            g.append(t)
        return sum(row == col for row in grid for col in g)
