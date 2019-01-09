class Solution {
    private char[] cs;
    private char[][] board;
    public boolean exist(char[][] board, String word) {
        this.cs = word.toCharArray();
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == cs[0] && backtrack(i, j, 0)) return true;
            }
        }
        return false;
    }
    private boolean backtrack(int i, int j, int k) {
        if (board[i][j] == cs[k]) {
            if (k == cs.length - 1) return true;
            char cache = board[i][j];
            board[i][j] = 0;
            k++;
            if ( i > 0 && backtrack(i - 1, j, k)
                    || i < board.length - 1 && backtrack(i + 1, j, k)
                    || j > 0 && backtrack(i, j - 1, k)
                    || j < board[i].length - 1 && backtrack(i, j + 1, k)) {
                return true;
            }
            board[i][j] = cache;
        }
        return false;
    }
}