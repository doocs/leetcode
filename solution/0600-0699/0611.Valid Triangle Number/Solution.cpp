class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0, n = nums.size();
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int k = lower_bound(nums.begin() + j + 1, nums.end(), nums[i] + nums[j]) - nums.begin() - 1;
                ans += k - j;
            }
        }
        return ans;
    }
};