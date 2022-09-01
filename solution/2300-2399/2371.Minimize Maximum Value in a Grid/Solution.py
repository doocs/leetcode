class Solution:
    def minScore(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        nums = [(v, i, j) for i, row in enumerate(grid) for j, v in enumerate(row)]
        nums.sort()
        row_max = [0] * m
        col_max = [0] * n
        ans = [[0] * n for _ in range(m)]
        for _, i, j in nums:
            ans[i][j] = max(row_max[i], col_max[j]) + 1
            row_max[i] = col_max[j] = ans[i][j]
        return ans
