class Solution {
    public String tictactoe(int[][] moves) {
        int n = moves.length;
        int[] counter = new int[8];
        for (int i = n - 1; i >= 0; i -= 2) {
            int row = moves[i][0], col = moves[i][1];
            ++counter[row];
            ++counter[col + 3];
            if (row == col) ++counter[6];
            if (row + col == 2) ++counter[7];
            if (counter[row] == 3 || counter[col + 3] == 3 || counter[6] == 3 || counter[7] == 3) {
                return (i % 2) == 0 ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
}