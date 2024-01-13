class Solution {
public:
    int rearrangeSticks(int n, int k) {
        const int mod = 1e9 + 7;
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (f[i - 1][j - 1] + (i - 1LL) * f[i - 1][j]) % mod;
            }
        }
        return f[n][k];
    }
};