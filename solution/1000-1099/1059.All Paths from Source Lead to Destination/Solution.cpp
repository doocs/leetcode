class Solution {
public:
    bool leadsToDestination(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        vector<int> f(n);
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        function<bool(int)> dfs = [&](int i) {
            if (i == destination) {
                return g[i].empty();
            }
            if (f[i]) {
                return f[i] == 1;
            }
            if (vis[i] || g[i].empty()) {
                return false;
            }
            vis[i] = true;
            for (int j : g[i]) {
                if (!dfs(j)) {
                    f[i] = -1;
                    return false;
                }
            }
            f[i] = 1;
            return true;
        };
        return dfs(source);
    }
};