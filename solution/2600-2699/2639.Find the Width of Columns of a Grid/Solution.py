class Solution:
    def findColumnWidth(self, grid: List[List[int]]) -> List[int]:
        ans = [0] * len(grid[0])
        for row in grid:
            for j, x in enumerate(row):
                w = len(str(x))
                ans[j] = max(ans[j], w)
        return ans
