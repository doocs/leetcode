class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            ok = grid1[i][j]
            grid2[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid2[x][y] and not dfs(x, y):
                    ok = 0
            return ok

        m, n = len(grid1), len(grid1[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(dfs(i, j) for i in range(m) for j in range(n) if grid2[i][j])
