class Solution {
public:
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<char> vis(n);
        vis[source] = 1;
        vector<int> stk{source};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            for (int j : g[i]) {
                if (j == destination) {
                    return true;
                }
                if (!vis[j]) {
                    vis[j] = 1;
                    stk.push_back(j);
                }
            }
        }
        return false;
    }
};
