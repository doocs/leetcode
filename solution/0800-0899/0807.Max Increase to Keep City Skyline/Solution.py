class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        row_max = [max(row) for row in grid]
        col_max = [max(col) for col in zip(*grid)]
        return sum(
            min(row_max[i], col_max[j]) - x
            for i, row in enumerate(grid)
            for j, x in enumerate(row)
        )
