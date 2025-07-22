class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        int f[n][k + 1][2];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (j < 0) {
                return 0;
            }
            if (i >= n) {
                return j == 0 ? 1 : 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int a = 1LL * cnt1 * dfs(i + 1, j, 1) % mod;
            int b = 1LL * cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0) % mod;
            return f[i][j][k] = (a + b) % mod;
        };
        return dfs(0, k, 1);
    }
};
