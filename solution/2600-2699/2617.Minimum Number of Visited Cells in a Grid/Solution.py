class Solution:
    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dist = [[-1] * n for _ in range(m)]
        dist[0][0] = 1
        row = [[] for _ in range(m)]
        col = [[] for _ in range(n)]
        for i in range(m):
            for j in range(n):
                while row[i] and grid[i][row[i][0][1]] + row[i][0][1] < j:
                    heappop(row[i])
                if row[i] and (dist[i][j] == -1 or dist[i][j] > row[i][0][0] + 1):
                    dist[i][j] = row[i][0][0] + 1
                while col[j] and grid[col[j][0][1]][j] + col[j][0][1] < i:
                    heappop(col[j])
                if col[j] and (dist[i][j] == -1 or dist[i][j] > col[j][0][0] + 1):
                    dist[i][j] = col[j][0][0] + 1
                if dist[i][j] != -1:
                    heappush(row[i], (dist[i][j], j))
                    heappush(col[j], (dist[i][j], i))
        return dist[-1][-1]
