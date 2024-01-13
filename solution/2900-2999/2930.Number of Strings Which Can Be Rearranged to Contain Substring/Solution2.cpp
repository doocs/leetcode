class Solution {
public:
    int stringCount(int n) {
        const int mod = 1e9 + 7;
        using ll = long long;
        auto qpow = [&](ll a, int n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ll a = qpow(25, n);
        ll b = a;
        ll c = (qpow(25, n) + n * qpow(25, n - 1) % mod) % mod;
        ll ab = qpow(24, n);
        ll ac = (qpow(24, n) + n * qpow(24, n - 1) % mod) % mod;
        ll bc = ac;
        ll abc = (qpow(23, n) + n * qpow(23, n - 1) % mod) % mod;
        ll tot = qpow(26, n);
        return ((tot - (a + b + c - ab - ac - bc + abc)) % mod + mod) % mod;
    }
};