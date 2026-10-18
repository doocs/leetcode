class Solution:
    def lastVisitedCell(self, grid: list[list[str]]) -> list[int]:
        m, n = len(grid), len(grid[0])
        r = c = 0
        vis = [[False] * n for _ in range(m)]
        while 1:
            cur = [r, c]
            vis[r][c] = True
            match grid[r][c]:
                case "L":
                    c -= 1
                case "R":
                    c += 1
                case "U":
                    r -= 1
                case "D":
                    r += 1
            if 0 <= r < m and 0 <= c < n and not vis[r][c]:
                continue
            return cur
