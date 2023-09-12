class Solution {
public:
    int countVowelPermutation(int n) {
        using ll = long long;
        vector<ll> f(5, 1);
        const int mod = 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            vector<ll> g(5);
            g[0] = (f[1] + f[2] + f[4]) % mod;
            g[1] = (f[0] + f[2]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[2];
            g[4] = (f[2] + f[3]) % mod;
            f = move(g);
        }
        return accumulate(f.begin(), f.end(), 0LL) % mod;
    }
};