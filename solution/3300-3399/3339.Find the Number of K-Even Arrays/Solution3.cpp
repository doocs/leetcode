class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        vector<vector<int>> f(k + 1, vector<int>(2));
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        const int mod = 1e9 + 7;
        f[0][1] = 1;

        for (int i = 0; i < n; ++i) {
            vector<vector<int>> g(k + 1, vector<int>(2));
            for (int j = 0; j <= k; ++j) {
                g[j][0] = (1LL * cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0)) % mod) % mod;
                g[j][1] = (1LL * cnt1 * (f[j][0] + f[j][1]) % mod) % mod;
            }
            f = g;
        }
        return (f[k][0] + f[k][1]) % mod;
    }
};
