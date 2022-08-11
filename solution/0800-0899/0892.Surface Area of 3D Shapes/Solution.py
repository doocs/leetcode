class Solution:
    def surfaceArea(self, grid: List[List[int]]) -> int:
        ans = 0
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    ans += 2 + v * 4
                    if i:
                        ans -= min(v, grid[i - 1][j]) * 2
                    if j:
                        ans -= min(v, grid[i][j - 1]) * 2
        return ans
