class Solution {
    public int numRookCaptures(char[][] board) {
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        int res = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    for (int[] direction : directions) {
                        res += search(board, i, j, direction);
                    }
                    return res;
                }
            }
        }
        return res;
    }

    private int search(char[][] board, int i, int j, int[] direction) {
        while (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (board[i][j] == 'B') return 0;
            if (board[i][j] == 'p') return 1;
            i += direction[0];
            j += direction[1];
        }
        return 0;
    }
}