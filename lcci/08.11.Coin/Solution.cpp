class Solution {
public:
    int waysToChange(int n) {
        const int mod = 1e9 + 7;
        vector<int> coins = {25, 10, 5, 1};
        int f[5][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= 4; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod;
                }
            }
        }
        return f[4][n];
    }
};