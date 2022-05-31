class Solution:
    def minimumObstacles(self, grid: List[List[int]]) -> int:
        q = deque([(0, 0, 0)])
        m, n = len(grid), len(grid[0])
        vis = set()
        while q:
            i, j, k = q.popleft()
            if i == m - 1 and j == n - 1:
                return k
            if (i, j) in vis:
                continue
            vis.add((i, j))
            for a, b in ((0, -1), (0, 1), (-1, 0), (1, 0)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n:
                    if grid[x][y] == 0:
                        q.appendleft((x, y, k))
                    else:
                        q.append((x, y, k + 1))
        return 0
