class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> res;
        vector<int> path(n, 0);
        vector<bool> used(n, false);
        sort(nums.begin(), nums.end());
        dfs(0, n, nums, used, path, res);
        return res;
    }

    void dfs(int u, int n, vector<int>& nums, vector<bool>& used, vector<int>& path, vector<vector<int>>& res) {
        if (u == n) {
            res.emplace_back(path);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            path[u] = nums[i];
            used[i] = true;
            dfs(u + 1, n, nums, used, path, res);
            used[i] = false;
        }
    }
};