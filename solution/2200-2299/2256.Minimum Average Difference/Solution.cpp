typedef long long ll;

class Solution {
public:
    int minimumAverageDifference(vector<int>& nums) {
        int n = nums.size();
        vector<ll> s(n);
        s[0] = nums[0];
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + nums[i];
        int ans = 0;
        ll mi = LONG_MAX;
        for (int i = 0; i < n; ++i) {
            ll a = s[i] / (i + 1);
            ll b = i == n - 1 ? 0 : (s[n - 1] - s[i]) / (n - i - 1);
            ll t = abs(a - b);
            if (mi > t) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
};