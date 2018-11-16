class Solution {
    private char[][] board;
    private boolean[][] rows;
    private boolean[][] cols;
    private boolean[][] subBox;

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.rows = new boolean[9][9];
        this.cols = new boolean[9][9];
        this.subBox = new boolean[9][9];
        int val;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    val = board[i][j] - '0';
                    rows[i][val - 1] = true;
                    cols[j][val - 1] = true;
                    subBox[(i / 3) * 3 + j / 3][val - 1] = true;
                }
            }
        }
        fillSudoku(0, 0);
    }

    private boolean fillSudoku(int i, int j) {
        if (i == 9) return true;
        int x = i, y = j;
        if (y == 8) {
            x++;
            y = 0;
        } else y++;
        if (board[i][j] == '.') {
            for (int k = 1; k <= 9; k++) {
                if (!rows[i][k - 1] && !cols[j][k - 1] && !subBox[(i / 3) * 3 + j / 3][k - 1]) {
                    rows[i][k - 1] = true;
                    cols[j][k - 1] = true;
                    subBox[(i / 3) * 3 + j / 3][k - 1] = true;
                    if (fillSudoku(x, y)) {
                        board[i][j] = (char) (k + '0');
                        return true;
                    }
                    rows[i][k - 1] = false;
                    cols[j][k - 1] = false;
                    subBox[(i / 3) * 3 + j / 3][k - 1] = false;
                }
            }
            return false;
        } else return fillSudoku(x, y);
    }
}