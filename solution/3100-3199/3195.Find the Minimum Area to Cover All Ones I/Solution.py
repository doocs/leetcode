class Solution:
    def minimumArea(self, grid: List[List[int]]) -> int:
        x1 = y1 = inf
        x2 = y2 = -inf
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x == 1:
                    x1 = min(x1, i)
                    y1 = min(y1, j)
                    x2 = max(x2, i)
                    y2 = max(y2, j)
        return (x2 - x1 + 1) * (y2 - y1 + 1)
