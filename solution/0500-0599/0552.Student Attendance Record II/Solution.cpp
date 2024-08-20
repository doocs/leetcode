class Solution {
public:
    int checkRecord(int n) {
        int f[n][2][3];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        auto dfs = [&](auto&& dfs, int i, int j, int k) -> int {
            if (i >= n) {
                return 1;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = dfs(dfs, i + 1, j, 0);
            if (j == 0) {
                ans = (ans + dfs(dfs, i + 1, j + 1, 0)) % mod;
            }
            if (k < 2) {
                ans = (ans + dfs(dfs, i + 1, j, k + 1)) % mod;
            }
            return f[i][j][k] = ans;
        };
        return dfs(dfs, 0, 0, 0);
    }
};
