class Solution {
public:
    int maxScore(vector<int> parent, vector<int> op, vector<int> score) {
        int n = parent.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; i++) {
            g[parent[i]].push_back(i);
        }
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (g[i].empty()) {
                return score[i];
            }
            int tot = score[i];
            int mn = INT_MAX;
            for (int j : g[i]) {
                int t = dfs(j);
                mn = min(mn, t);
                tot += t;
            }
            if (op[i] == 0) {
                return tot - min(0, mn);
            }
            return tot - mn;
        };
        return dfs(0);
    }
};