class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m, n = len(board), len(board[0])
        p = list(range(m * n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O':
                    if i == 0 or j == 0 or i == m - 1 or j == n - 1:
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for x, y in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                            if board[i + x][j + y] == "O":
                                p[find(i * n + j)] = find((i + x) * n + j + y)

        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O' and find(i * n + j) != find(m * n):
                    board[i][j] = 'X'
