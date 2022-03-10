class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0, m, n, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int cur, int m, int n, char[][] board, String word) {
        if (cur == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(cur)) {
            return false;
        }
        board[i][j] += 256;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (dfs(x, y, cur + 1, m, n, board, word)) {
                return true;
            }
        }
        board[i][j] -= 256;
        return false;
    }
}