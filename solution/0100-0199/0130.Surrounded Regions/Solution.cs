public class Solution {
    private readonly int[] dirs = {-1, 0, 1, 0, -1};
    private char[][] board;
    private int m;
    private int n;

    public void Solve(char[][] board) {
        m = board.Length;
        n = board[0].Length;
        this.board = board;

        for (int i = 0; i < m; ++i) {
            Dfs(i, 0);
            Dfs(i, n - 1);
        }
        for (int j = 0; j < n; ++j) {
            Dfs(0, j);
            Dfs(m - 1, j);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void Dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '.';
        for (int k = 0; k < 4; ++k) {
            Dfs(i + dirs[k], j + dirs[k + 1]);
        }
    }
}