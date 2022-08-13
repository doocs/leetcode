class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> t;
        vector<vector<int>> ans;
        dfs(0, t, nums, ans);
        return ans;
    }

    void dfs(int u, vector<int>& t, vector<int>& nums, vector<vector<int>>& ans) {
        if (u == nums.size()) {
            ans.push_back(t);
            return;
        }
        t.push_back(nums[u]);
        dfs(u + 1, t, nums, ans);
        t.pop_back();
        dfs(u + 1, t, nums, ans);
    }
};