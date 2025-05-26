class Solution {
public:
    int numTilings(int n) {
        const int mod = 1e9 + 7;
        long long f[4] = {1, 0, 0, 0};
        for (int i = 1; i <= n; ++i) {
            long long g[4];
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
            g[1] = (f[2] + f[3]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[0];
            memcpy(f, g, sizeof(g));
        }
        return f[0];
    }
};