class Solution {
    public String tictactoe(String[] board) {
        int n = board.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int dg = 0, udg = 0;
        boolean hasEmptyGrid = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i].charAt(j);
                if (c == ' ') {
                    hasEmptyGrid = true;
                    continue;
                }
                int v = c == 'X' ? 1 : -1;
                rows[i] += v;
                cols[j] += v;
                if (i == j) {
                    dg += v;
                }
                if (i + j + 1 == n) {
                    udg += v;
                }
                if (Math.abs(rows[i]) == n || Math.abs(cols[j]) == n || Math.abs(dg) == n
                    || Math.abs(udg) == n) {
                    return String.valueOf(c);
                }
            }
        }
        return hasEmptyGrid ? "Pending" : "Draw";
    }
}