class Solution:
    def matrixMedian(self, grid: List[List[int]]) -> int:
        def count(x):
            return sum(bisect_right(row, x) for row in grid)

        m, n = len(grid), len(grid[0])
        target = (m * n + 1) >> 1
        return bisect_left(range(10**6 + 1), target, key=count)
