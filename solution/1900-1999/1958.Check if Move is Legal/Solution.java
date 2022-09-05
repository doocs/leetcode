class Solution {
    private static final int[][] DIRS
        = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final int N = 8;

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        for (int[] d : DIRS) {
            int i = rMove, j = cMove;
            int t = 0;
            int a = d[0], b = d[1];
            while (0 <= i + a && i + a < N && 0 <= j + b && j + b < N) {
                ++t;
                i += a;
                j += b;
                if (board[i][j] == '.' || board[i][j] == color) {
                    break;
                }
            }
            if (board[i][j] == color && t > 1) {
                return true;
            }
        }
        return false;
    }
}