class TicTacToe {
    private int n;
    private int[][] counter;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        counter = new int[2][(n << 1) + 2];
        this.n = n;
    }

    /**
       Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        counter[player - 1][row] += 1;
        counter[player - 1][col + n] += 1;
        if (row == col) {
            counter[player - 1][n << 1] += 1;
        }
        if (row + col == n - 1) {
            counter[player - 1][(n << 1) + 1] += 1;
        }
        if (counter[player - 1][row] == n || counter[player - 1][col + n] == n
            || counter[player - 1][n << 1] == n || counter[player - 1][(n << 1) + 1] == n) {
            return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */