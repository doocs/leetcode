class Solution {
public:
    vector<vector<int>> findSubsequences(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        dfs(0, -1000, t, nums, ans);
        return ans;
    }

    void dfs(int u, int last, vector<int>& t, vector<int>& nums, vector<vector<int>>& ans) {
        if (u == nums.size()) {
            if (t.size() > 1) ans.push_back(t);
            return;
        }
        if (nums[u] >= last) {
            t.push_back(nums[u]);
            dfs(u + 1, nums[u], t, nums, ans);
            t.pop_back();
        }
        if (nums[u] != last) dfs(u + 1, last, t, nums, ans);
    }
};