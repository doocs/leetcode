class Solution {
public:
    int minReorder(int n, vector<vector<int>>& connections) {
        unordered_map<int, vector<pair<int, bool>>> g;
        for (auto& e : connections) {
            int u = e[0], v = e[1];
            g[u].push_back({v, true});
            g[v].push_back({u, false});
        }
        vector<bool> vis(n);
        return dfs(0, g, vis);
    }

    int dfs(int u, unordered_map<int, vector<pair<int, bool>>>& g, vector<bool>& vis) {
        vis[u] = true;
        int ans = 0;
        for (auto& p : g[u]) {
            int v = p.first;
            bool exist = p.second;
            if (!vis[v]) {
                if (exist) ++ans;
                ans += dfs(v, g, vis);
            }
        }
        return ans;
    }
};