class Solution:
    def minimumTime(self, grid: List[List[int]]) -> int:
        if grid[0][1] > 1 and grid[1][0] > 1:
            return -1
        m, n = len(grid), len(grid[0])
        dist = [[inf] * n for _ in range(m)]
        dist[0][0] = 0
        q = [(0, 0, 0)]
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            t, i, j = heappop(q)
            if i == m - 1 and j == n - 1:
                return t
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n:
                    nt = t + 1
                    if nt < grid[x][y]:
                        nt = grid[x][y] + (grid[x][y] - nt) % 2
                    if nt < dist[x][y]:
                        dist[x][y] = nt
                        heappush(q, (nt, x, y))
