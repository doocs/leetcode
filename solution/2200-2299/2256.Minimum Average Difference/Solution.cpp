class Solution {
public:
    int minimumAverageDifference(vector<int>& nums) {
        int n = nums.size();
        using ll = long long;
        ll pre = 0;
        ll suf = accumulate(nums.begin(), nums.end(), 0LL);
        int ans = 0;
        ll mi = suf;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            suf -= nums[i];
            ll a = pre / (i + 1);
            ll b = n - i - 1 == 0 ? 0 : suf / (n - i - 1);
            ll t = abs(a - b);
            if (t < mi) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
};