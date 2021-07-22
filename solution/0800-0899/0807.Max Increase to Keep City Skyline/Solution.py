class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        we = [max(item) for item in grid]
        ns = [max([grid[i][j] for i in range(m)]) for j in range(n)]
        res = 0
        for i in range(m):
            for j in range(n):
                res += min(we[i], ns[j]) - grid[i][j]
        return res
