class Solution {
public:
    int numberOfWays(string corridor) {
        int n = corridor.size();
        int f[n][3];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        auto dfs = [&](this auto&& dfs, int i, int k) -> int {
            if (i >= n) {
                return k == 2;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            k += corridor[i] == 'S';
            if (k > 2) {
                return 0;
            }
            f[i][k] = dfs(i + 1, k);
            if (k == 2) {
                f[i][k] = (f[i][k] + dfs(i + 1, 0)) % mod;
            }
            return f[i][k];
        };
        return dfs(0, 0);
    }
};
