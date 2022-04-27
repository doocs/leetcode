class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        INF = 0x3F3F3F3F
        m, n = len(heights), len(heights[0])
        dist = [[INF] * n for _ in range(m)]
        dist[0][0] = 0
        dirs = [-1, 0, 1, 0, -1]
        q = [(0, 0, 0)]
        while q:
            t, i, j = heappop(q)
            for k in range(4):
                x, y = i + dirs[k], j + dirs[k + 1]
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and max(t, abs(heights[x][y] - heights[i][j])) < dist[x][y]
                ):
                    dist[x][y] = max(t, abs(heights[x][y] - heights[i][j]))
                    heappush(q, (dist[x][y], x, y))
        return dist[-1][-1]
