using ll = long long;

class Solution {
public:
    int knightDialer(int n) {
        if (n == 1) return 10;
        int mod = 1e9 + 7;
        vector<ll> f(10, 1ll);
        while (--n) {
            vector<ll> t(10);
            t[0] = f[4] + f[6];
            t[1] = f[6] + f[8];
            t[2] = f[7] + f[9];
            t[3] = f[4] + f[8];
            t[4] = f[0] + f[3] + f[9];
            t[6] = f[0] + f[1] + f[7];
            t[7] = f[2] + f[6];
            t[8] = f[1] + f[3];
            t[9] = f[2] + f[4];
            for (int i = 0; i < 10; ++i) f[i] = t[i] % mod;
        }
        ll ans = accumulate(f.begin(), f.end(), 0ll);
        return (int) (ans % mod);
    }
};