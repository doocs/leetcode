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
            MarkConnected(i, 0);
            MarkConnected(i, n - 1);
        }
        for (int j = 0; j < n; ++j) {
            MarkConnected(0, j);
            MarkConnected(m - 1, j);
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

    private void MarkConnected(int i, int j) {
        if (board[i][j] != 'O') {
            return;
        }
        board[i][j] = '.';
        var stack = new Stack<int[]>();
        stack.Push(new[] {i, j});
        while (stack.Count > 0) {
            int[] cell = stack.Pop();
            for (int k = 0; k < 4; ++k) {
                int x = cell[0] + dirs[k];
                int y = cell[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                    board[x][y] = '.';
                    stack.Push(new[] {x, y});
                }
            }
        }
    }
}
