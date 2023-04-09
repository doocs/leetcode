class Solution:
    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        row = [0] * m
        col = [0] * n
        q = deque([(1, 0, 0)])
        while q:
            dist, i, j = q.popleft()
            if i == m - 1 and j == n - 1:
                return dist
            for k in range(max(row[i], j) + 1, min(n, j + grid[i][j] + 1)):
                q.append((dist + 1, i, k))
                row[i] = k
            for k in range(max(col[j], i) + 1, min(m, i + grid[i][j] + 1)):
                q.append((dist + 1, k, j))
                col[j] = k
        return -1
