class Solution:
    def checkValidGrid(self, grid: List[List[int]]) -> bool:
        if grid[0][0]:
            return False
        n = len(grid)
        pos = [None] * (n * n)
        for i in range(n):
            for j in range(n):
                pos[grid[i][j]] = (i, j)
        for (x1, y1), (x2, y2) in pairwise(pos):
            dx, dy = abs(x1 - x2), abs(y1 - y2)
            ok = (dx == 1 and dy == 2) or (dx == 2 and dy == 1)
            if not ok:
                return False
        return True
