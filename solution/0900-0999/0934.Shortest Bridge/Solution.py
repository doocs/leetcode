class Solution:
    def shortestBridge(self, grid: List[List[int]]) -> int:
        def find():
            for i in range(m):
                for j in range(n):
                    if grid[i][j] == 1:
                        return i, j

        def dfs(i, j):
            q.append((i, j))
            grid[i][j] = 2
            for a, b in dirs:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y)

        m, n = len(grid), len(grid[0])
        q = deque()
        dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        i, j = find()
        dfs(i, j)
        ans = -1
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in dirs:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        if grid[x][y] == 1:
                            return ans
                        if grid[x][y] == 0:
                            grid[x][y] = 2
                            q.append((x, y))
        return 0
