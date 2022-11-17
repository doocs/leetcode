class Solution:
    def validTicTacToe(self, board: List[str]) -> bool:
        def win(x):
            for i in range(3):
                if all(board[i][j] == x for j in range(3)):
                    return True
                if all(board[j][i] == x for j in range(3)):
                    return True
            if all(board[i][i] == x for i in range(3)):
                return True
            return all(board[i][2 - i] == x for i in range(3))

        x = sum(board[i][j] == 'X' for i in range(3) for j in range(3))
        o = sum(board[i][j] == 'O' for i in range(3) for j in range(3))
        if x != o and x - 1 != o:
            return False
        if win('X') and x - 1 != o:
            return False
        return not (win('O') and x != o)
