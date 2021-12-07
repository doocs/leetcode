class Solution:
    def findBall(self, grid: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])

        def dfs(i, j):
            nonlocal m, n
            if i == m:
                return j
            if j == 0 and grid[i][j] == -1:
                return -1
            if j == n - 1 and grid[i][j] == 1:
                return -1
            if grid[i][j] == 1 and grid[i][j + 1] == -1:
                return -1
            if grid[i][j] == -1 and grid[i][j - 1] == 1:
                return -1
            return dfs(i + 1, j + 1) if grid[i][j] == 1 else dfs(i + 1, j - 1)

        return [dfs(0, j) for j in range(n)]
