class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        ranges::sort(nums);
        for (int i = 1; i < nums.size(); i++) {
            nums[i] += nums[i - 1];
        }
        vector<int> ans;
        for (const auto& q : queries) {
            ans.emplace_back(upper_bound(nums.begin(), nums.end(), q) - nums.begin());
        }
        return ans;
    }
};
