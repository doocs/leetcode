class Solution {
public:
    long long maximumProduct(vector<int>& nums, int m) {
        long long ans = LLONG_MIN;
        int mx = INT_MIN;
        int mi = INT_MAX;
        for (int i = m - 1; i < nums.size(); ++i) {
            int x = nums[i];
            int y = nums[i - m + 1];
            mi = min(mi, y);
            mx = max(mx, y);
            ans = max(ans, max(1LL * x * mi, 1LL * x * mx));
        }
        return ans;
    }
};
