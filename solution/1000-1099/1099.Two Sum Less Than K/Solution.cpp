class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1, n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = lower_bound(nums.begin() + i + 1, nums.end(), k - nums[i]) - nums.begin() - 1;
            if (i < j) {
                ans = max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }
};