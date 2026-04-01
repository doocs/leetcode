class Solution:
    def minOperations(self, grid: list[list[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        mx = max(max(row) for row in grid)

        def check(target: int) -> int:
            diff = [[0] * (n + 2) for _ in range(m + 2)]
            total_ops = 0

            for i, row in enumerate(grid, 1):
                for j, val in enumerate(row, 1):
                    diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1]

                    cur_val = val + diff[i][j]

                    if cur_val > target:
                        return -1

                    if cur_val < target:
                        if i + k - 1 > m or j + k - 1 > n:
                            return -1

                        needed = target - cur_val
                        total_ops += needed
                        diff[i][j] += needed
                        diff[i + k][j] -= needed
                        diff[i][j + k] -= needed
                        diff[i + k][j + k] += needed
            return total_ops

        for t in range(mx, mx + 2):
            res = check(t)
            if res != -1:
                return res

        return -1
