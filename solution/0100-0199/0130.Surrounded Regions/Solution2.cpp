class Solution {
public:
    vector<int> p;

    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1)
                        p[find(i * n + j)] = find(m * n);
                    else {
                        for (int k = 0; k < 4; ++k) {
                            int x = i + dirs[k], y = j + dirs[k + 1];
                            if (board[x][y] == 'O') p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (board[i][j] == 'O' && find(i * n + j) != find(m * n))
                    board[i][j] = 'X';
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};