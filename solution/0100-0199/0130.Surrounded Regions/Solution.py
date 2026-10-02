class Solution:
    def solve(self, board: List[List[str]]) -> None:
        m, n = len(board), len(board[0])
        stack = []
        for i in range(m):
            if board[i][0] == "O":
                board[i][0] = "."
                stack.append((i, 0))
            if board[i][n - 1] == "O":
                board[i][n - 1] = "."
                stack.append((i, n - 1))
        for j in range(n):
            if board[0][j] == "O":
                board[0][j] = "."
                stack.append((0, j))
            if board[m - 1][j] == "O":
                board[m - 1][j] = "."
                stack.append((m - 1, j))
        while stack:
            i, j = stack.pop()
            for a, b in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and board[x][y] == "O":
                    board[x][y] = "."
                    stack.append((x, y))
        for i in range(m):
            for j in range(n):
                if board[i][j] == ".":
                    board[i][j] = "O"
                elif board[i][j] == "O":
                    board[i][j] = "X"
