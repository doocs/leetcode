class Solution:
    def maxSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(1, m - 1):
            for j in range(1, n - 1):
                s = -grid[i][j - 1] - grid[i][j + 1]
                s += sum(
                    grid[x][y] for x in range(i - 1, i + 2) for y in range(j - 1, j + 2)
                )
                ans = max(ans, s)
        return ans
