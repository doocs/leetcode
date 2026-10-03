class Solution {
public:
    int numberOfWays(string corridor) {
        int n = corridor.size();
        const int mod = 1e9 + 7;
        vector<vector<int>> f(n + 1, vector<int>(3));
        f[n][2] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int k = 0; k < 3; ++k) {
                int nk = k + (corridor[i] == 'S');
                if (nk > 2) {
                    continue;
                }
                f[i][k] = f[i + 1][nk];
                if (nk == 2) {
                    f[i][k] = (f[i][k] + f[i + 1][0]) % mod;
                }
            }
        }
        return f[0][0];
    }
};
