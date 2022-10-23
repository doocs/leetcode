using ll = long long;

class Solution {
public:
    long long minCost(vector<int>& nums, vector<int>& cost) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {nums[i], cost[i]};
        sort(arr.begin(), arr.end());
        vector<ll> f(n + 1), g(n + 1);
        for (int i = 1; i <= n; ++i) {
            auto [a, b] = arr[i - 1];
            f[i] = f[i - 1] + 1ll * a * b;
            g[i] = g[i - 1] + b;
        }
        ll ans = 1e18;
        for (int i = 1; i <= n; ++i) {
            auto [a, _] = arr[i - 1];
            ll l = 1ll * a * g[i - 1] - f[i - 1];
            ll r = f[n] - f[i] - 1ll * a * (g[n] - g[i]);
            ans = min(ans, l + r);
        }
        return ans;
    }
};