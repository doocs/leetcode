class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        int n = nums.size();
        for (int mask = 0; mask < 1 << n; ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask >> i) & 1) {
                    t.push_back(nums[i]);
                }
            }
            ans.push_back(t);
        }
        return ans;
    }
};