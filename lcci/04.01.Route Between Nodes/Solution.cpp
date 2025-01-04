class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (auto& e : graph) {
            g[e[0]].push_back(e[1]);
        }
        auto dfs = [&](this auto&& dfs, int i) -> bool {
            if (i == target) {
                return true;
            }
            if (vis[i]) {
                return false;
            }
            vis[i] = true;
            for (int j : g[i]) {
                if (dfs(j)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(start);
    }
};
