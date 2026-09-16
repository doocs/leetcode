class Solution {
public:
    int numberOfSets(int n, int k) {
        const int mod = 1e9 + 7;
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        vector<vector<int>> g(n + 1, vector<int>(k + 1));
        f[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
                g[i][j] = g[i - 1][j];
                if (j) {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
                }
            }
        }
        return (f[n][k] + g[n][k]) % mod;
    }
};
