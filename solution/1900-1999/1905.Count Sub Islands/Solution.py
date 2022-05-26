class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(i, j):
            ans = grid1[i][j] == 1
            grid2[i][j] = 0
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid2[x][y] == 1 and not dfs(x, y):
                    ans = False
            return ans

        m, n = len(grid1), len(grid1[0])
        return sum(grid2[i][j] == 1 and dfs(i, j) for i in range(m) for j in range(n))
