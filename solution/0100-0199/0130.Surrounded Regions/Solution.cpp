class Solution {
public:
    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        vector<pair<int, int>> stack;
        auto mark = [&](int i, int j) {
            if (board[i][j] == 'O') {
                board[i][j] = '.';
                stack.emplace_back(i, j);
            }
        };
        for (int i = 0; i < m; ++i) {
            mark(i, 0);
            mark(i, n - 1);
        }
        for (int j = 1; j < n - 1; ++j) {
            mark(0, j);
            mark(m - 1, j);
        }
        while (!stack.empty()) {
            auto [i, j] = stack.back();
            stack.pop_back();
            if (i > 0) mark(i - 1, j);
            if (i + 1 < m) mark(i + 1, j);
            if (j > 0) mark(i, j - 1);
            if (j + 1 < n) mark(i, j + 1);
        }
        for (auto& row : board) {
            for (auto& c : row) {
                if (c == '.') {
                    c = 'O';
                } else if (c == 'O') {
                    c = 'X';
                }
            }
        }
    }
};
