class Solution {
public:
    vector<vector<int>> candyCrush(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        bool run = true;
        while (run) {
            run = false;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n - 2; ++j) {
                    if (board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j + 1]) && abs(board[i][j]) == abs(board[i][j + 2])) {
                        run = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -abs(board[i][j]);
                    }
                }
            }
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i < m - 2; ++i) {
                    if (board[i][j] != 0 && abs(board[i][j]) == abs(board[i + 1][j]) && abs(board[i][j]) == abs(board[i + 2][j])) {
                        run = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -abs(board[i][j]);
                    }
                }
            }
            if (run) {
                for (int j = 0; j < n; ++j) {
                    int curr = m - 1;
                    for (int i = m - 1; i >= 0; --i) {
                        if (board[i][j] > 0) {
                            board[curr][j] = board[i][j];
                            --curr;
                        }
                    }
                    while (curr > -1) {
                        board[curr][j] = 0;
                        --curr;
                    }
                }
            }
        }
        return board;
    }
};