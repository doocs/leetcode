class Solution {
public:
    long long maxSubarraySum(vector<int>& nums, int k) {
        using ll = long long;
        ll inf = 1e18;
        vector<ll> f(k, inf);
        ll ans = -inf;
        ll s = 0;
        f[k - 1] = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            ans = max(ans, s - f[i % k]);
            f[i % k] = min(f[i % k], s);
        }
        return ans;
    }
};
