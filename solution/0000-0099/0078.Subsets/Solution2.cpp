class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> ans;
        for (int mask = 0; mask < 1 << n; ++mask) {
            vector<int> t;
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    t.emplace_back(nums[i]);
                }
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};