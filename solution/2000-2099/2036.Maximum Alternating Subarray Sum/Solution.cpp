using ll = long long;

class Solution {
public:
    long long maximumAlternatingSubarraySum(vector<int>& nums) {
        ll ans = nums[0];
        ll a = nums[0], b = -(1 << 30);
        for (int i = 1; i < nums.size(); ++i) {
            ll c = a, d = b;
            a = max(1ll * nums[i], d + nums[i]);
            b = c - nums[i];
            ans = max(ans, max(a, b));
        }
        return ans;
    }
};