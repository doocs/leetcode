class Solution {
public:
    string tictactoe(vector<string>& board) {
        int n = board.size();
        vector<int> rows(n), cols(n);
        int dg = 0, udg = 0;
        bool hasEmptyGrid = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i][j];
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
                if (abs(rows[i]) == n || abs(cols[j]) == n || abs(dg) == n || abs(udg) == n) {
                    return string(1, c);
                }
            }
        }
        return hasEmptyGrid ? "Pending" : "Draw";
    }
};