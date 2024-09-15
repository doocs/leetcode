class Solution:
    def findSafeWalk(self, grid: List[List[int]], health: int) -> bool:
        m, n = len(grid), len(grid[0])
        dist = [[inf] * n for _ in range(m)]
        dist[0][0] = grid[0][0]
        q = deque([(0, 0)])
        dirs = (-1, 0, 1, 0, -1)
        while q:
            x, y = q.popleft()
            for a, b in pairwise(dirs):
                nx, ny = x + a, y + b
                if (
                    0 <= nx < m
                    and 0 <= ny < n
                    and dist[nx][ny] > dist[x][y] + grid[nx][ny]
                ):
                    dist[nx][ny] = dist[x][y] + grid[nx][ny]
                    q.append((nx, ny))
        return dist[-1][-1] < health
