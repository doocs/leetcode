class Solution:
    def flipChess(self, chessboard: List[str]) -> int:
        def bfs(i: int, j: int) -> int:
            q = deque([(i, j)])
            g = [list(row) for row in chessboard]
            g[i][j] = "X"
            cnt = 0
            while q:
                i, j = q.popleft()
                for a, b in dirs:
                    x, y = i + a, j + b
                    while 0 <= x < m and 0 <= y < n and g[x][y] == "O":
                        x, y = x + a, y + b
                    if 0 <= x < m and 0 <= y < n and g[x][y] == "X":
                        x, y = x - a, y - b
                        cnt += max(abs(x - i), abs(y - j))
                        while x != i or y != j:
                            g[x][y] = "X"
                            q.append((x, y))
                            x, y = x - a, y - b
            return cnt

        m, n = len(chessboard), len(chessboard[0])
        dirs = [(a, b) for a in range(-1, 2) for b in range(-1, 2) if a != 0 or b != 0]
        return max(
            bfs(i, j) for i in range(m) for j in range(n) if chessboard[i][j] == "."
        )
