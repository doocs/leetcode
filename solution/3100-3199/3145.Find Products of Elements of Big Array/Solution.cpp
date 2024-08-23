using ll = long long;
const int m = 50;
ll cnt[m + 1];
ll s[m + 1];
ll p = 1;

auto init = [] {
    cnt[0] = 0;
    s[0] = 0;
    for (int i = 1; i <= m; ++i) {
        cnt[i] = cnt[i - 1] * 2 + p;
        s[i] = s[i - 1] * 2 + p * (i - 1);
        p *= 2;
    }
    return 0;
}();

pair<ll, ll> numIdxAndSum(ll x) {
    ll idx = 0;
    ll totalSum = 0;
    while (x > 0) {
        int i = 63 - __builtin_clzll(x);
        idx += cnt[i];
        totalSum += s[i];
        x -= 1LL << i;
        totalSum += (x + 1) * i;
        idx += x + 1;
    }
    return make_pair(idx, totalSum);
}

ll f(ll i) {
    ll l = 0;
    ll r = 1LL << m;
    while (l < r) {
        ll mid = (l + r + 1) >> 1;
        auto idxAndSum = numIdxAndSum(mid);
        ll idx = idxAndSum.first;
        if (idx < i) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    auto idxAndSum = numIdxAndSum(l);
    ll totalSum = idxAndSum.second;
    ll idx = idxAndSum.first;
    i -= idx;
    ll x = l + 1;
    for (int j = 0; j < i; ++j) {
        ll y = x & -x;
        totalSum += __builtin_ctzll(y);
        x -= y;
    }
    return totalSum;
}

ll qpow(ll a, ll n, ll mod) {
    ll ans = 1 % mod;
    a = a % mod;
    while (n > 0) {
        if (n & 1) {
            ans = ans * a % mod;
        }
        a = a * a % mod;
        n >>= 1;
    }
    return ans;
}

class Solution {
public:
    vector<int> findProductsOfElements(vector<vector<ll>>& queries) {
        int n = queries.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ll left = queries[i][0];
            ll right = queries[i][1];
            ll mod = queries[i][2];
            ll power = f(right + 1) - f(left);
            ans[i] = static_cast<int>(qpow(2, power, mod));
        }
        return ans;
    }
};
