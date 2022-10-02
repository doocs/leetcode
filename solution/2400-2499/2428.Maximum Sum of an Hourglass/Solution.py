class Solution:
    def maxSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(1, m - 1):
            for j in range(1, n - 1):
                t = 0
                for x in [i - 1, i, i + 1]:
                    for y in [j - 1, j, j + 1]:
                        t += grid[x][y]

                t -= grid[i][j - 1]
                t -= grid[i][j + 1]
                ans = max(ans, t)
        return ans
