class Solution {
public:
    long long getMaxFunctionValue(vector<int>& receiver, long long k) {
        int n = receiver.size(), m = 64 - __builtin_clzll(k);
        int f[n][m];
        long long g[n][m];
        for (int i = 0; i < n; ++i) {
            f[i][0] = receiver[i];
            g[i][0] = i;
        }
        for (int j = 1; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                f[i][j] = f[f[i][j - 1]][j - 1];
                g[i][j] = g[i][j - 1] + g[f[i][j - 1]][j - 1];
            }
        }
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int p = i;
            long long t = 0;
            for (int j = 0; j < m; ++j) {
                if (k >> j & 1) {
                    t += g[p][j];
                    p = f[p][j];
                }
            }
            ans = max(ans, p + t);
        }
        return ans;
    }
};