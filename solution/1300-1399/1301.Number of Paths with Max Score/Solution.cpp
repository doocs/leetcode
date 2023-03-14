class Solution {
public:
    vector<int> pathsWithMaxScore(vector<string>& board) {
        int n = board.size();
        const int mod = 1e9 + 7;
        int f[n][n];
        int g[n][n];
        memset(f, -1, sizeof(f));
        memset(g, 0, sizeof(g));
        f[n - 1][n - 1] = 0;
        g[n - 1][n - 1] = 1;

        auto update = [&](int i, int j, int x, int y) {
            if (x >= n || y >= n || f[x][y] == -1 || board[i][j] == 'X' || board[i][j] == 'S') {
                return;
            }
            if (f[x][y] > f[i][j]) {
                f[i][j] = f[x][y];
                g[i][j] = g[x][y];
            } else if (f[x][y] == f[i][j]) {
                g[i][j] = (g[i][j] + g[x][y]) % mod;
            }
        };

        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                update(i, j, i + 1, j);
                update(i, j, i, j + 1);
                update(i, j, i + 1, j + 1);
                if (f[i][j] != -1) {
                    if (board[i][j] >= '0' && board[i][j] <= '9') {
                        f[i][j] += (board[i][j] - '0');
                    }
                }
            }
        }
        vector<int> ans(2);
        if (f[0][0] != -1) {
            ans[0] = f[0][0];
            ans[1] = g[0][0];
        }
        return ans;
    }
};