class Solution {
public:
    vector<int> findPattern(vector<vector<int>>& board, vector<string>& pattern) {
        int m = board.size(), n = board[0].size();
        int r = pattern.size(), c = pattern[0].size();
        auto check = [&](int i, int j) {
            vector<int> d1(26, -1);
            vector<int> d2(10, -1);
            for (int a = 0; a < r; ++a) {
                for (int b = 0; b < c; ++b) {
                    int x = i + a, y = j + b;
                    if (isdigit(pattern[a][b])) {
                        int v = pattern[a][b] - '0';
                        if (v != board[x][y]) {
                            return false;
                        }
                    } else {
                        int v = pattern[a][b] - 'a';
                        if (d1[v] != -1 && d1[v] != board[x][y]) {
                            return false;
                        }
                        if (d2[board[x][y]] != -1 && d2[board[x][y]] != v) {
                            return false;
                        }
                        d1[v] = board[x][y];
                        d2[board[x][y]] = v;
                    }
                }
            }
            return true;
        };
        for (int i = 0; i < m - r + 1; ++i) {
            for (int j = 0; j < n - c + 1; ++j) {
                if (check(i, j)) {
                    return {i, j};
                }
            }
        }
        return {-1, -1};
    }
};