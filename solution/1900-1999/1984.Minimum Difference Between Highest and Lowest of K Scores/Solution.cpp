class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1e5;
        for (int i = 0; i < nums.size() - k + 1; ++i)
            ans = min(ans, nums[i + k - 1] - nums[i]);
        return ans;
    }
};