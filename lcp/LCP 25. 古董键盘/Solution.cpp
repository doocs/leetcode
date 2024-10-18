class Solution {
public:
    int keyboard(int k, int n) {
        int f[n + 1][27];
        memset(f, 0, sizeof(f));
        for (int j = 0; j < 27; ++j) {
            f[0][j] = 1;
        }
        int c[n + 1][k + 1];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        const int mod = 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= k; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < 27; ++j) {
                for (int h = 0; h <= min(i, k); ++h) {
                    f[i][j] = (f[i][j] + 1LL * f[i - h][j - 1] * c[i][h] % mod) % mod;
                }
            }
        }
        return f[n][26];
    }
};
