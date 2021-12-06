class Solution {
    public int numRookCaptures(char[][] board) {
        int[] pos = find(board);
        int ans = 0, n = 8;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {
            int x = pos[0], y = pos[1], a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n && board[x + a][y + b] != 'B') {
                x += a;
                y += b;
                if (board[x][y] == 'p') {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    private int[] find(char[][] board) {
        int n = 8;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}