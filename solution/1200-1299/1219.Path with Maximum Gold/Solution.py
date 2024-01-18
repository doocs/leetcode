class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            if not (0 <= i < m and 0 <= j < n and grid[i][j]):
                return 0
            v = grid[i][j]
            grid[i][j] = 0
            ans = max(dfs(i + a, j + b) for a, b in pairwise(dirs)) + v
            grid[i][j] = v
            return ans

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        return max(dfs(i, j) for i in range(m) for j in range(n))
