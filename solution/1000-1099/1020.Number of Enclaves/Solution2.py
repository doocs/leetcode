class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = deque()
        for j in range(n):
            for i in (0, m - 1):
                if grid[i][j]:
                    q.append((i, j))
                    grid[i][j] = 0
        for i in range(m):
            for j in (0, n - 1):
                if grid[i][j]:
                    q.append((i, j))
                    grid[i][j] = 0
        dirs = (-1, 0, 1, 0, -1)
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    q.append((x, y))
                    grid[x][y] = 0
        return sum(sum(row) for row in grid)
