class Solution:
    def colorBorder(
        self, grid: List[List[int]], row: int, col: int, color: int
    ) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        vis = [[False] * n for _ in range(m)]

        def dfs(i, j, color):
            vis[i][j] = True
            old_color = grid[i][j]
            for a, b in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
                x, y = a + i, b + j
                if 0 <= x < m and 0 <= y < n:
                    if not vis[x][y]:
                        if grid[x][y] == old_color:
                            dfs(x, y, color)
                        else:
                            grid[i][j] = color
                else:
                    grid[i][j] = color

        dfs(row, col, color)
        return grid
