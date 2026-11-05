class Solution {
public:
    vector<vector<int>> findDisappearedNumbers(vector<int>& nums, int lower, int upper) {
        sort(nums.begin(), nums.end());
        int l = lower_bound(nums.begin(), nums.end(), lower) - nums.begin();
        int r = lower_bound(nums.begin(), nums.end(), upper + 1) - nums.begin();
        vector<vector<int>> ans;
        int pre = lower;
        for (int i = l; i < r; i++) {
            int cur = nums[i];
            if (pre < cur) {
                ans.push_back({pre, cur - 1});
            }
            pre = cur + 1;
        }
        if (pre <= upper) {
            ans.push_back({pre, upper});
        }
        return ans;
    }
};