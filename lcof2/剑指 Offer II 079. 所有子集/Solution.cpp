class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> t;
        dfs(0, nums, t, res);
        return res;
    }

    void dfs(int i, vector<int>& nums, vector<int> t, vector<vector<int>>& res) {
        res.push_back(t);
        if (i == nums.size()) return;
        for (int j = i; j < nums.size(); ++j) {
            t.push_back(nums[j]);
            dfs(j + 1, nums, t, res);
            t.pop_back();
        }
    }
};