class Solution {
public:
    int superPow(int a, vector<int>& b) {
        using ll = long long;
        const int mod = 1337;
        ll ans = 1;
        auto qpow = [&](ll a, int n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        for (int i = b.size() - 1; ~i; --i) {
            ans = ans * qpow(a, b[i]) % mod;
            a = qpow(a, 10);
        }
        return ans;
    }
};