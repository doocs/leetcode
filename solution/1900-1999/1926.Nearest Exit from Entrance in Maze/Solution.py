class Solution:
    def nearestExit(self, maze: List[List[str]], entrance: List[int]) -> int:
        m, n = len(maze), len(maze[0])
        i, j = entrance
        q = deque([(i, j)])
        maze[i][j] = '+'
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and maze[x][y] == '.':
                        if x == 0 or x == m - 1 or y == 0 or y == n - 1:
                            return ans
                        q.append((x, y))
                        maze[x][y] = '+'
        return -1
