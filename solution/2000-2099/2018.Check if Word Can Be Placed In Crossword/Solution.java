class Solution {
    private int m;
    private int n;
    private char[][] board;
    private String word;
    private int k;

    public boolean placeWordInCrossword(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        this.word = word;
        k = word.length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean leftToRight = (j == 0 || board[i][j - 1] == '#') && check(i, j, 0, 1);
                boolean rightToLeft = (j == n - 1 || board[i][j + 1] == '#') && check(i, j, 0, -1);
                boolean upToDown = (i == 0 || board[i - 1][j] == '#') && check(i, j, 1, 0);
                boolean downToUp = (i == m - 1 || board[i + 1][j] == '#') && check(i, j, -1, 0);
                if (leftToRight || rightToLeft || upToDown || downToUp) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int i, int j, int a, int b) {
        int x = i + a * k, y = j + b * k;
        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') {
            return false;
        }
        for (int p = 0; p < k; ++p) {
            if (i < 0 || i >= m || j < 0 || j >= n
                || (board[i][j] != ' ' && board[i][j] != word.charAt(p))) {
                return false;
            }
            i += a;
            j += b;
        }
        return true;
    }
}