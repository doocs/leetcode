class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> t;
        dfs(1, n, k, t, res);
        return res;
    }

    void dfs(int i, int n, int k, vector<int> t, vector<vector<int>>& res) {
        if (t.size() == k) {
            res.push_back(t);
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.push_back(j);
            dfs(j + 1, n, k, t, res);
            t.pop_back();
        }
    }
};