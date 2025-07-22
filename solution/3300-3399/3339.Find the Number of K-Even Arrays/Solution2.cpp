class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        int f[n + 1][k + 1][2];
        memset(f, 0, sizeof(f));
        f[0][0][1] = 1;
        const int mod = 1e9 + 7;
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j][0] = 1LL * (f[i - 1][j][1] + (j ? f[i - 1][j - 1][0] : 0)) * cnt0 % mod;
                f[i][j][1] = 1LL * (f[i - 1][j][0] + f[i - 1][j][1]) * cnt1 % mod;
            }
        }
        return (f[n][k][0] + f[n][k][1]) % mod;
    }
};
