class Solution {
public:
    int stringCount(int n) {
        const int mod = 1e9 + 7;
        using ll = long long;
        ll f[n + 1][2][3][2];
        memset(f, -1, sizeof(f));
        function<ll(int, int, int, int)> dfs = [&](int i, int l, int e, int t) -> ll {
            if (i == 0) {
                return l == 1 && e == 2 && t == 1 ? 1 : 0;
            }
            if (f[i][l][e][t] != -1) {
                return f[i][l][e][t];
            }
            ll a = dfs(i - 1, l, e, t) * 23 % mod;
            ll b = dfs(i - 1, min(1, l + 1), e, t) % mod;
            ll c = dfs(i - 1, l, min(2, e + 1), t) % mod;
            ll d = dfs(i - 1, l, e, min(1, t + 1)) % mod;
            return f[i][l][e][t] = (a + b + c + d) % mod;
        };
        return dfs(n, 0, 0, 0);
    }
};