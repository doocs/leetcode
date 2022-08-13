class Solution {
public:
    int countHousePlacements(int n) {
        int mod = 1e9 + 7;
        vector<vector<long>> f(n, vector<long>(2));
        f[0] = {1, 1};
        for (int i = 1; i < n; ++i) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1]) % mod;
            f[i][1] = f[i - 1][0];
        }
        long s = f[n - 1][0] + f[n - 1][1];
        return (int)((s * s) % mod);
    }
};