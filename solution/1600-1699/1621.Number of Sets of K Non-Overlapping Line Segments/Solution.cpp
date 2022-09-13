class Solution {
public:
    int f[1010][1010];
    int g[1010][1010];
    const int mod = 1e9 + 7;

    int numberOfSets(int n, int k) {
        memset(f, 0, sizeof(f));
        memset(g, 0, sizeof(g));
        f[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
                g[i][j] = g[i - 1][j];
                if (j > 0) {
                    g[i][j] += f[i - 1][j - 1];
                    g[i][j] %= mod;
                    g[i][j] += g[i - 1][j - 1];
                    g[i][j] %= mod;
                }
            }
        }
        return (f[n][k] + g[n][k]) % mod;
    }
};