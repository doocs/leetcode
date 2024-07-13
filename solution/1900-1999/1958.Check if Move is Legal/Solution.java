class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a == 0 && b == 0) {
                    continue;
                }
                int i = rMove, j = cMove;
                int cnt = 0;
                while (0 <= i + a && i + a < 8 && 0 <= j + b && j + b < 8) {
                    i += a;
                    j += b;
                    if (++cnt > 1 && board[i][j] == color) {
                        return true;
                    }
                    if (board[i][j] == color || board[i][j] == '.') {
                        break;
                    }
                }
            }
        }
        return false;
    }
}