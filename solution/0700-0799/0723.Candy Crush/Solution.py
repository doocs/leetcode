class Solution:
    def candyCrush(self, board: List[List[int]]) -> List[List[int]]:
        m, n = len(board), len(board[0])
        run = True
        while run:
            run = False
            for i in range(m):
                for j in range(2, n):
                    if board[i][j] and abs(board[i][j]) == abs(board[i][j - 1]) == abs(
                        board[i][j - 2]
                    ):
                        run = True
                        board[i][j] = board[i][j - 1] = board[i][j - 2] = -abs(
                            board[i][j]
                        )
            for j in range(n):
                for i in range(2, m):
                    if board[i][j] and abs(board[i][j]) == abs(board[i - 1][j]) == abs(
                        board[i - 2][j]
                    ):
                        run = True
                        board[i][j] = board[i - 1][j] = board[i - 2][j] = -abs(
                            board[i][j]
                        )
            if run:
                for j in range(n):
                    k = m - 1
                    for i in range(m - 1, -1, -1):
                        if board[i][j] > 0:
                            board[k][j] = board[i][j]
                            k -= 1
                    while k >= 0:
                        board[k][j] = 0
                        k -= 1
        return board
