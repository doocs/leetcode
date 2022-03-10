class Solution {
    private char[][] board;
    private String word;
    private int m;
    private int n;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        this.word = word;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || word.charAt(k) != board[i][j]) {
            return false;
        }
        board[i][j] = ' ';
        int[] dirs = {-1, 0, 1, 0, -1};
        boolean ans = false;
        for (int l = 0; l < 4; ++l) {
            ans = ans || dfs(i + dirs[l], j + dirs[l + 1], k + 1);
        }
        board[i][j] = word.charAt(k);
        return ans;
    }
}