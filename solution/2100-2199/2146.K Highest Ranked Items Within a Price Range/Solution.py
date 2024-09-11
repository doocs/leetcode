class Solution:
    def highestRankedKItems(
        self, grid: List[List[int]], pricing: List[int], start: List[int], k: int
    ) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        row, col = start
        low, high = pricing
        q = deque([(row, col)])
        pq = []
        if low <= grid[row][col] <= high:
            pq.append((0, grid[row][col], row, col))
        grid[row][col] = 0
        dirs = (-1, 0, 1, 0, -1)
        step = 0
        while q:
            step += 1
            for _ in range(len(q)):
                x, y = q.popleft()
                for a, b in pairwise(dirs):
                    nx, ny = x + a, y + b
                    if 0 <= nx < m and 0 <= ny < n and grid[nx][ny] > 0:
                        if low <= grid[nx][ny] <= high:
                            pq.append((step, grid[nx][ny], nx, ny))
                        grid[nx][ny] = 0
                        q.append((nx, ny))
        pq.sort()
        return [list(x[2:]) for x in pq[:k]]
