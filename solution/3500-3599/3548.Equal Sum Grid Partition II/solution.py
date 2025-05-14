from typing import List

class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])

        total_sum = sum(sum(row) for row in grid)

        # Try horizontal cuts
        row_prefix_sum = 0
        for i in range(m - 1):  # cut between row i and i+1
            row_prefix_sum += sum(grid[i])
            if row_prefix_sum * 2 == total_sum:
                return True

        # Try vertical cuts
        col_sums = [0] * n
        for i in range(m):
            for j in range(n):
                col_sums[j] += grid[i][j]

        col_prefix_sum = 0
        for j in range(n - 1):  # cut between column j and j+1
            col_prefix_sum += col_sums[j]
            if col_prefix_sum * 2 == total_sum:
                return True

        return False
