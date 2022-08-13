class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    int dfs(int u, int cost, vector<vector<int>>& g, vector<bool>& hasApple, vector<bool>& vis) {
        if (vis[u]) return 0;
        vis[u] = true;
        int nxt = 0;
        for (int& v : g[u]) nxt += dfs(v, 2, g, hasApple, vis);
        if (!hasApple[u] && !nxt) return 0;
        return cost + nxt;
    }
};