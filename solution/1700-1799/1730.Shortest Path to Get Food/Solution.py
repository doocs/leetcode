class Solution:
    def getFood(self, grid: List[List[str]]) -> int:
        def pos():
            for i in range(m):
                for j in range(n):
                    if grid[i][j] == '*':
                        return i, j

        m, n = len(grid), len(grid[0])
        q = deque([pos()])
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        if grid[x][y] == '#':
                            return ans
                        if grid[x][y] == 'O':
                            grid[x][y] = 'X'
                            q.append((x, y))
        return -1
