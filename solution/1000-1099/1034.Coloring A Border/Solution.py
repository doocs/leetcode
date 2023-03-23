class Solution:
    def colorBorder(
        self, grid: List[List[int]], row: int, col: int, color: int
    ) -> List[List[int]]:
        def dfs(i: int, j: int, c: int) -> None:
            vis[i][j] = True
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n:
                    if not vis[x][y]:
                        if grid[x][y] == c:
                            dfs(x, y, c)
                        else:
                            grid[i][j] = color
                else:
                    grid[i][j] = color

        m, n = len(grid), len(grid[0])
        vis = [[False] * n for _ in range(m)]
        dfs(row, col, grid[row][col])
        return grid
