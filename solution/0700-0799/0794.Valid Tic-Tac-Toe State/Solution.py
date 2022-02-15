class Solution:
    def validTicTacToe(self, board: List[str]) -> bool:
        def win(p):
            for i in range(3):
                if board[i][0] == board[i][1] == board[i][2] == p:
                    return True
                if board[0][i] == board[1][i] == board[2][i] == p:
                    return True
            if board[0][0] == board[1][1] == board[2][2] == p:
                return True
            return board[0][2] == board[1][1] == board[2][0] == p

        x, o = 0, 0
        for i in range(3):
            for j in range(3):
                if board[i][j] == 'X':
                    x += 1
                elif board[i][j] == 'O':
                    o += 1

        if x != o and x - 1 != o:
            return False

        if win('X') and x - 1 != o:
            return False

        return not (win('O') and x != o)
