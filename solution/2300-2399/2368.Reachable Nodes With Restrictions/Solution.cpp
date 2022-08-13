class Solution {
public:
    int ans;

    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<bool> vis(n);
        for (int v : restricted) vis[v] = true;
        ans = 0;
        dfs(0, g, vis);
        return ans;
    }

    void dfs(int u, vector<vector<int>>& g, vector<bool>& vis) {
        if (vis[u]) return;
        vis[u] = true;
        ++ans;
        for (int v : g[u]) dfs(v, g, vis);
    }
};