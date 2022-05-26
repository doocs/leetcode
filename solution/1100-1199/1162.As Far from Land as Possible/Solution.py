class Solution:
    def maxDistance(self, grid: List[List[int]]) -> int:
        n = len(grid)
        q = deque([(i, j) for i in range(n) for j in range(n) if grid[i][j] == 1])
        ans = -1
        valid = False
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                    x, y = i + a, b + j
                    if 0 <= x < n and 0 <= y < n and grid[x][y] == 0:
                        valid = True
                        grid[x][y] = 1
                        q.append((x, y))
        return ans if valid else -1
