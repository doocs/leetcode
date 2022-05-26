class Solution:
    def trapRainWater(self, heightMap: List[List[int]]) -> int:
        m, n = len(heightMap), len(heightMap[0])
        vis = [[False] * n for _ in range(m)]
        pq = []
        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                    heappush(pq, (heightMap[i][j], i, j))
                    vis[i][j] = True

        ans = 0
        while pq:
            e = heappop(pq)
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                i, j = e[1] + x, e[2] + y
                if i >= 0 and i < m and j >= 0 and j < n and not vis[i][j]:
                    if heightMap[i][j] < e[0]:
                        ans += e[0] - heightMap[i][j]
                    vis[i][j] = True
                    heappush(pq, (max(heightMap[i][j], e[0]), i, j))
        return ans
