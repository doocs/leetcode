class Solution {
public:
    int stringCount(int n) {
        const int mod = 1e9 + 7;
        using ll = long long;
        ll f[2][3][2]{};
        f[1][2][1] = 1;
        for (int i = 0; i < n; ++i) {
            ll g[2][3][2]{};
            for (int l = 0; l < 2; ++l) {
                for (int e = 0; e < 3; ++e) {
                    for (int t = 0; t < 2; ++t) {
                        ll a = f[l][e][t] * 23;
                        ll b = f[min(1, l + 1)][e][t];
                        ll c = f[l][min(2, e + 1)][t];
                        ll d = f[l][e][min(1, t + 1)];
                        g[l][e][t] = (a + b + c + d) % mod;
                    }
                }
            }
            memcpy(f, g, sizeof(f));
        }
        return f[0][0][0];
    }
};
