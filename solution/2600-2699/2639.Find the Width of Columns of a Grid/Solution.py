class Solution:
    def findColumnWidth(self, grid: List[List[int]]) -> List[int]:
        n = len(grid[0])
        ans = [0] * n
        for row in grid:
            for j, x in enumerate(row):
                w = len(str(x))
                ans[j] = max(ans[j], w)
        return ans
