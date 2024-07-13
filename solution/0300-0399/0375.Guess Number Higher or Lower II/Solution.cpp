class Solution {
public:
    int getMoneyAmount(int n) {
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; i; --i) {
            for (int j = i + 1; j <= n; ++j) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; ++k) {
                    f[i][j] = min(f[i][j], max(f[i][k - 1], f[k + 1][j]) + k);
                }
            }
        }
        return f[1][n];
    }
};