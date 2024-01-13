class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (i >= candidates.size() || s < candidates[i]) {
                return;
            }
            int x = candidates[i];
            t.emplace_back(x);
            dfs(i + 1, s - x);
            t.pop_back();
            while (i < candidates.size() && candidates[i] == x) {
                ++i;
            }
            dfs(i, s);
        };
        dfs(0, target);
        return ans;
    }
};