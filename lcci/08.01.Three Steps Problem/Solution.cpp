class Solution {
public:
    int waysToStep(int n) {
        if (n < 4) {
            return pow(2, n - 1);
        }
        vector<vector<ll>> a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        vector<vector<ll>> res = qpow(a, n - 4);
        ll ans = 0;
        for (ll x : res[0]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

private:
    using ll = long long;
    const int mod = 1e9 + 7;
    vector<vector<ll>> mul(vector<vector<ll>>& a, vector<vector<ll>>& b) {
        int m = a.size(), n = b[0].size();
        vector<vector<ll>> c(m, vector<ll>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j] % mod) % mod;
                }
            }
        }
        return c;
    }

    vector<vector<ll>> qpow(vector<vector<ll>>& a, int n) {
        vector<vector<ll>> res = {{4, 2, 1}};
        while (n) {
            if (n & 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }
};