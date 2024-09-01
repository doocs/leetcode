class Solution {
public:
    int maxScore(vector<vector<int>>& grid) {
        int m = grid.size();
        int mx = 0;
        bool g[101][11]{};
        for (int i = 0; i < m; ++i) {
            for (int x : grid[i]) {
                g[x][i] = true;
                mx = max(mx, x);
            }
        }
        int f[mx + 1][1 << m];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= mx; ++i) {
            for (int j = 0; j < 1 << m; ++j) {
                f[i][j] = f[i - 1][j];
                for (int k = 0; k < m; ++k) {
                    if (g[i][k] && (j >> k & 1) == 1) {
                        f[i][j] = max(f[i][j], f[i - 1][j ^ 1 << k] + i);
                    }
                }
            }
        }
        return f[mx][(1 << m) - 1];
    }
};
