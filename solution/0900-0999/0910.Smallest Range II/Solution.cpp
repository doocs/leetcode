class Solution {
public:
    int smallestRangeII(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = nums[n - 1] - nums[0];
        for (int i = 1; i < n; ++i) {
            int mi = min(nums[0] + k, nums[i] - k);
            int mx = max(nums[i - 1] + k, nums[n - 1] - k);
            ans = min(ans, mx - mi);
        }
        return ans;
    }
};