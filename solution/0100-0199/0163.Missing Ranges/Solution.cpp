class Solution {
public:
    vector<vector<int>> findMissingRanges(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        if (n == 0) {
            return {{lower, upper}};
        }
        vector<vector<int>> ans;
        if (nums[0] > lower) {
            ans.push_back({lower, nums[0] - 1});
        }
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] - nums[i - 1] > 1) {
                ans.push_back({nums[i - 1] + 1, nums[i] - 1});
            }
        }
        if (nums[n - 1] < upper) {
            ans.push_back({nums[n - 1] + 1, upper});
        }
        return ans;
    }
};