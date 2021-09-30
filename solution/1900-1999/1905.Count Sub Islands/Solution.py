class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(grid1, grid2, i, j, m, n) -> bool:
            res = grid1[i][j] == 1
            grid2[i][j] = 0
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                a, b = i + x, j + y
                if a >= 0 and a < m and b >= 0 and b < n and grid2[a][b] == 1:
                    if not dfs(grid1, grid2, a, b, m, n):
                        res = False
            return res

        m, n = len(grid1), len(grid1[0])
        count = 0
        for i in range(m):
            for j in range(n):
                if grid2[i][j] == 1 and dfs(grid1, grid2, i, j, m, n):
                    count += 1
        return count
