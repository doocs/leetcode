class Solution {
public:
    int minNonZeroProduct(int p) {
        using ll = long long;
        const int mod = 1e9 + 7;
        auto qpow = [](ll a, ll n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ll a = ((1LL << p) - 1) % mod;
        ll b = qpow(((1LL << p) - 2) % mod, (1L << (p - 1)) - 1);
        return a * b % mod;
    }
};