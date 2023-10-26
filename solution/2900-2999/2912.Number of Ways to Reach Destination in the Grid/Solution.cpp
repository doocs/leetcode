class Solution {
public:
    int numberOfWays(int n, int m, int k, vector<int>& source, vector<int>& dest) {
        const int mod = 1e9 + 7;
        vector<long long> f(4);
        f[0] = 1;
        while (k--) {
            vector<long long> g(4);
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod;
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod;
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod;
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod;
            f = move(g);
        }
        if (source[0] == dest[0]) {
            return source[1] == dest[1] ? f[0] : f[2];
        }
        return source[1] == dest[1] ? f[1] : f[3];
    }
};