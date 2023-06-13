class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) -> void {
            if (i == nums.size()) {
                ans.push_back(t);
                return;
            }
            dfs(i + 1);
            t.push_back(nums[i]);
            dfs(i + 1);
            t.pop_back();
        };
        dfs(0);
        return ans;
    }
};