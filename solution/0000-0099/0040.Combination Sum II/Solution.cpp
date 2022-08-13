class Solution {
public:
    vector<int> candidates;
    vector<vector<int>> ans;
    vector<int> t;
    int target;

    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        this->candidates = candidates;
        this->target = target;
        vector<int> t;
        dfs(0, 0, t);
        return ans;
    }

    void dfs(int u, int s, vector<int>& t) {
        if (s > target) return;
        if (s == target) {
            ans.push_back(t);
            return;
        }
        for (int i = u; i < candidates.size(); ++i) {
            if (i > u && candidates[i] == candidates[i - 1]) continue;
            t.push_back(candidates[i]);
            dfs(i + 1, s + candidates[i], t);
            t.pop_back();
        }
    }
};