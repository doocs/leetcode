class Solution {
public:
    int knightDialer(int n) {
        const int mod = 1e9 + 7;
        vector<long long> f(10, 1);
        while (--n) {
            vector<long long> g(10);
            g[0] = (f[4] + f[6]) % mod;
            g[1] = (f[6] + f[8]) % mod;
            g[2] = (f[7] + f[9]) % mod;
            g[3] = (f[4] + f[8]) % mod;
            g[4] = (f[0] + f[3] + f[9]) % mod;
            g[6] = (f[0] + f[1] + f[7]) % mod;
            g[7] = (f[2] + f[6]) % mod;
            g[8] = (f[1] + f[3]) % mod;
            g[9] = (f[2] + f[4]) % mod;
            f = g;
        }
        return accumulate(f.begin(), f.end(), 0LL) % mod;
    }
};
