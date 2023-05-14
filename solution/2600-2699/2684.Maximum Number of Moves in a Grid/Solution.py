class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        dirs = ((-1, 1), (0, 1), (1, 1))
        m, n = len(grid), len(grid[0])
        q = deque((i, 0) for i in range(m))
        dist = [[0] * n for _ in range(m)]
        ans = 0
        while q:
            i, j = q.popleft()
            for a, b in dirs:
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and grid[x][y] > grid[i][j]
                    and dist[x][y] < dist[i][j] + 1
                ):
                    dist[x][y] = dist[i][j] + 1
                    ans = max(ans, dist[x][y])
                    q.append((x, y))
        return ans
