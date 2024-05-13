class Solution:
    def maxScore(self, grid: List[List[int]]) -> int:
        f = [[0] * len(grid[0]) for _ in range(len(grid))]
        ans = -inf
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                mi = inf
                if i:
                    mi = min(mi, f[i - 1][j])
                if j:
                    mi = min(mi, f[i][j - 1])
                ans = max(ans, x - mi)
                f[i][j] = min(x, mi)
        return ans
