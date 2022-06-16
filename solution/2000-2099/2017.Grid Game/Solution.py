class Solution:
    def gridGame(self, grid: List[List[int]]) -> int:
        ans = inf
        s1, s2 = sum(grid[0]), 0
        for j, v in enumerate(grid[0]):
            s1 -= v
            ans = min(ans, max(s1, s2))
            s2 += grid[1][j]
        return ans
