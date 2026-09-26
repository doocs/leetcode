class Solution {
public:
    int checkRecord(int n) {
        const int mod = 1e9 + 7;
        int f[2][3] = {{1, 1, 1}, {1, 1, 1}};
        for (int i = 0; i < n; ++i) {
            int g[2][3]{};
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 3; ++k) {
                    int ans = f[j][0];
                    if (j == 0) {
                        ans = (ans + f[1][0]) % mod;
                    }
                    if (k < 2) {
                        ans = (ans + f[j][k + 1]) % mod;
                    }
                    g[j][k] = ans % mod;
                }
            }
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 3; ++k) {
                    f[j][k] = g[j][k];
                }
            }
        }
        return f[0][0];
    }
};
