class Solution {
public:
    vector<vector<int>> ans;
    vector<int> candidates;
    int target;

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        this->candidates = candidates;
        this->target = target;
        vector<int> t;
        dfs(0, 0, t);
        return ans;
    }

    void dfs(int s, int u, vector<int>& t) {
        if (s == target) {
            ans.push_back(t);
            return;
        }
        if (s > target) return;
        for (int i = u; i < candidates.size(); ++i) {
            int c = candidates[i];
            t.push_back(c);
            dfs(s + c, i, t);
            t.pop_back();
        }
    }
};