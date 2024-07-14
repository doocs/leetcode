class Solution:
    def numberOfRightTriangles(self, grid: List[List[int]]) -> int:
        rows = [0] * len(grid)
        cols = [0] * len(grid[0])
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                rows[i] += x
                cols[j] += x
        ans = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x:
                    ans += (rows[i] - 1) * (cols[j] - 1)
        return ans
