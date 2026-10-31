class Solution {
public:
    int uniquePaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int m = grid.size(), n = grid[0].size();
        vector<vector<array<int, 2>>> f(m, vector<array<int, 2>>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int r = j > 0 ? f[i][j - 1][0] : 0;
                int d = i > 0 ? f[i - 1][j][1] : 0;
                if (i == 0 && j == 0) {
                    d = 1;
                }
                if (grid[i][j] == 1) {
                    f[i][j] = {d, r};
                } else {
                    int s = (r + d) % mod;
                    f[i][j] = {s, s};
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
};
