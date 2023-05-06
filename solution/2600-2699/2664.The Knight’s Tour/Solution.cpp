class Solution {
public:
    vector<vector<int>> tourOfKnight(int m, int n, int r, int c) {
        vector<vector<int>> g(m, vector<int>(n, -1));
        g[r][c] = 0;
        int dirs[9] = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        bool ok = false;
        function<void(int, int)> dfs = [&](int i, int j) {
            if (g[i][j] == m * n - 1) {
                ok = true;
                return;
            }
            for (int k = 0; k < 8; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1) {
                    g[x][y] = g[i][j] + 1;
                    dfs(x, y);
                    if (ok) {
                        return;
                    }
                    g[x][y] = -1;
                }
            }
        };
        dfs(r, c);
        return g;
    }
};