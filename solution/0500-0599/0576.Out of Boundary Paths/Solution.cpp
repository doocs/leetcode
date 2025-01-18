class Solution {
public:
    int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int f[m][n][maxMove + 1];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return k >= 0;
            }
            if (k <= 0) {
                return 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = 0;
            for (int d = 0; d < 4; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                ans = (ans + dfs(x, y, k - 1)) % mod;
            }
            return f[i][j][k] = ans;
        };
        return dfs(startRow, startColumn, maxMove);
    }
};
