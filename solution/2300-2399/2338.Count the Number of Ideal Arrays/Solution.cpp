class Solution {
public:
    int idealArrays(int n, int maxValue) {
        const int mod = 1e9 + 7;
        vector<vector<int>> c(n, vector<int>(16));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                if (j == 0) {
                    c[i][j] = 1;
                } else {
                    c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
                }
            }
        }

        vector<vector<long long>> f(maxValue + 1, vector<long long>(16));
        for (int i = 1; i <= maxValue; ++i) {
            f[i][1] = 1;
        }

        for (int j = 1; j < 15; ++j) {
            for (int i = 1; i <= maxValue; ++i) {
                for (int k = 2; k * i <= maxValue; ++k) {
                    f[k * i][j + 1] = (f[k * i][j + 1] + f[i][j]) % mod;
                }
            }
        }

        long long ans = 0;
        for (int i = 1; i <= maxValue; ++i) {
            for (int j = 1; j < 16; ++j) {
                ans = (ans + f[i][j] * c[n - 1][j - 1]) % mod;
            }
        }

        return ans;
    }
};
