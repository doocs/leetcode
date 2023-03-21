class Solution {
public:
    long long largestEvenSum(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        long long ans = 0;
        int n = nums.size();
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        const int inf = 1 << 29;
        int mx1 = -inf, mx2 = -inf;
        for (int i = 0; i < n - k; ++i) {
            if (nums[i] % 2) {
                mx1 = nums[i];
            } else {
                mx2 = nums[i];
            }
        }
        int mi1 = inf, mi2 = inf;
        for (int i = n - 1; i >= n - k; --i) {
            if (nums[i] % 2) {
                mi2 = nums[i];
            } else {
                mi1 = nums[i];
            }
        }
        ans = max(ans - mi1 + mx1, ans - mi2 + mx2);
        return ans % 2 || ans < 0 ? -1 : ans;
    }
};