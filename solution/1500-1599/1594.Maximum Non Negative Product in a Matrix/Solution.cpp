class Solution {
public:
    int maxProductPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<array<long long, 2>>> f(m, vector<array<long long, 2>>(n));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long long x = grid[i][j];
                if (i == 0 && j == 0) {
                    f[i][j] = {x, x};
                    continue;
                }

                long long mn = LLONG_MAX, mx = LLONG_MIN;

                if (i > 0) {
                    auto [a, b] = f[i - 1][j];
                    mn = min(mn, min(a * x, b * x));
                    mx = max(mx, max(a * x, b * x));
                }

                if (j > 0) {
                    auto [a, b] = f[i][j - 1];
                    mn = min(mn, min(a * x, b * x));
                    mx = max(mx, max(a * x, b * x));
                }

                f[i][j] = {mn, mx};
            }
        }

        long long ans = f[m - 1][n - 1][1];
        const int mod = 1e9 + 7;
        return ans < 0 ? -1 : ans % mod;
    }
};
