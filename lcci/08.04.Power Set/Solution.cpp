class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        dfs(0, nums, t, ans);
        return ans;
    }

    void dfs(int u, vector<int>& nums, vector<int>& t, vector<vector<int>>& ans) {
        if (u == nums.size()) {
            ans.push_back(t);
            return;
        }
        dfs(u + 1, nums, t, ans);
        t.push_back(nums[u]);
        dfs(u + 1, nums, t, ans);
        t.pop_back();
    }
};