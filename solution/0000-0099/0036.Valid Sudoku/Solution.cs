public class Solution {
    public bool IsValidSudoku(char[][] board) {
        bool[,] row = new bool[9, 9];
        bool[,] col = new bool[9, 9];
        bool[,] sub = new bool[9, 9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int num = c - '0' - 1;
                int k = (i / 3) * 3 + (j / 3);
                if (row[i, num] || col[j, num] || sub[k, num]) {
                    return false;
                }
                row[i, num] = true;
                col[j, num] = true;
                sub[k, num] = true;
            }
        }
        return true;
    }
}
