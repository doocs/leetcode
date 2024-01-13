class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        g = [[0] * n for _ in range(n)]
        dist = [inf] * n
        vis = [False] * n
        for i, (x1, y1) in enumerate(points):
            for j in range(i + 1, n):
                x2, y2 = points[j]
                t = abs(x1 - x2) + abs(y1 - y2)
                g[i][j] = g[j][i] = t
        dist[0] = 0
        ans = 0
        for _ in range(n):
            i = -1
            for j in range(n):
                if not vis[j] and (i == -1 or dist[j] < dist[i]):
                    i = j
            vis[i] = True
            ans += dist[i]
            for j in range(n):
                if not vis[j]:
                    dist[j] = min(dist[j], g[i][j])
        return ans
