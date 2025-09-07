public class Solution {
    public void SolveSudoku(char[][] board) {
        bool[,] row = new bool[9, 9];
        bool[,] col = new bool[9, 9];
        bool[,,] block = new bool[3, 3, 9];
        bool ok = false;
        var t = new List<(int, int)>();

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    t.Add((i, j));
                } else {
                    int v = board[i][j] - '1';
                    row[i, v] = col[j, v] = block[i / 3, j / 3, v] = true;
                }
            }
        }

        void Dfs(int k) {
            if (k == t.Count) {
                ok = true;
                return;
            }
            var (i, j) = t[k];
            for (int v = 0; v < 9; ++v) {
                if (!row[i, v] && !col[j, v] && !block[i / 3, j / 3, v]) {
                    row[i, v] = col[j, v] = block[i / 3, j / 3, v] = true;
                    board[i][j] = (char)(v + '1');
                    Dfs(k + 1);
                    if (ok) return;
                    row[i, v] = col[j, v] = block[i / 3, j / 3, v] = false;
                }
            }
        }

        Dfs(0);
    }
}
