class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (visit(board, visited, i, j, rows, cols, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean visit(char[][] board, boolean[][] visited, int i, int j, int rows, int cols, String word) {
        if (word.length() == 0) {
            return true;
        }
        if (i < 0 || j < 0 || i >= rows || j >= cols || visited[i][j] || board[i][j] != word.charAt(0)) {
            return false;
        }

        visited[i][j] = true;
        String sub = word.substring(1);
        boolean res = visit(board, visited, i + 1, j, rows, cols, sub)
                || visit(board, visited, i - 1, j, rows, cols, sub)
                || visit(board, visited, i, j + 1, rows, cols, sub)
                || visit(board, visited, i, j - 1, rows, cols, sub);
        visited[i][j] = res;
        return res;

    }
}