class TicTacToe:
    def __init__(self, n: int):
        """
        Initialize your data structure here.
        """
        self.n = n
        self.counter = [[0] * ((n << 1) + 2) for _ in range(2)]

    def move(self, row: int, col: int, player: int) -> int:
        """
        Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins.
        """
        n = self.n
        self.counter[player - 1][row] += 1
        self.counter[player - 1][col + n] += 1
        if row == col:
            self.counter[player - 1][n << 1] += 1
        if row + col == n - 1:
            self.counter[player - 1][(n << 1) + 1] += 1
        if (
            self.counter[player - 1][row] == n
            or self.counter[player - 1][col + n] == n
            or self.counter[player - 1][n << 1] == n
            or self.counter[player - 1][(n << 1) + 1] == n
        ):
            return player
        return 0


# Your TicTacToe object will be instantiated and called as such:
# obj = TicTacToe(n)
# param_1 = obj.move(row,col,player)
