class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private char[][] board;
    private int m;
    private int n;

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            markConnected(i, 0);
            markConnected(i, n - 1);
        }
        for (int j = 0; j < n; ++j) {
            markConnected(0, j);
            markConnected(m - 1, j);
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

    private void markConnected(int i, int j) {
        if (board[i][j] != 'O') {
            return;
        }
        board[i][j] = '.';
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] {i, j});
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            for (int k = 0; k < 4; ++k) {
                int x = cell[0] + dirs[k];
                int y = cell[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                    board[x][y] = '.';
                    stack.push(new int[] {x, y});
                }
            }
        }
    }
}
