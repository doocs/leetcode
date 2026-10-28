class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int mx = 0;
        for (auto& row : grid) {
            for (int x : row) {
                mx = max(mx, x);
            }
        }
        mx = 1 << bit_width(static_cast<unsigned>(mx));
        vector<vector<vector<char>>> f(m, vector<vector<char>>(n, vector<char>(mx, 0)));
        f[0][0][grid[0][0]] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                if (i > 0 || j > 0) {
                    for (int k = 0; k < mx; k++) {
                        if (i > 0 && f[i - 1][j][k ^ x]) {
                            f[i][j][k] = 1;
                        }
                        if (j > 0 && f[i][j - 1][k ^ x]) {
                            f[i][j][k] = 1;
                        }
                    }
                }
            }
        }
        for (int k = 0;; k++) {
            if (f[m - 1][n - 1][k]) {
                return k;
            }
        }
    }
};