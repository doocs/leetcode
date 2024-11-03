class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        n, m = len(moveTime), len(moveTime[0])
        dist = [[inf] * m for _ in range(n)]
        dist[0][0] = 0
        pq = [(0, 0, 0)]
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            d, i, j = heappop(pq)
            if i == n - 1 and j == m - 1:
                return d
            if d > dist[i][j]:
                continue
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < m:
                    t = max(moveTime[x][y], dist[i][j]) + 1
                    if dist[x][y] > t:
                        dist[x][y] = t
                        heappush(pq, (t, x, y))
