class Solution {
public:
    long long maximumAlternatingSubarraySum(vector<int>& nums) {
        using ll = long long;
        const ll inf = 1LL << 60;
        ll ans = -inf, f = -inf, g = -inf;
        for (int x : nums) {
            ll ff = max(g, 0LL) + x;
            g = f - x;
            f = ff;
            ans = max({ans, f, g});
        }
        return ans;
    }
};