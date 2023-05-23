class Solution {
public:
    double frogPosition(int n, vector<vector<int>>& edges, int t, int target) {
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        queue<pair<int, double>> q{{{1, 1.0}}};
        bool vis[n + 1];
        memset(vis, false, sizeof(vis));
        vis[1] = true;
        for (; q.size() && t >= 0; --t) {
            for (int k = q.size(); k; --k) {
                auto [u, p] = q.front();
                q.pop();
                int cnt = g[u].size() - (u != 1);
                if (u == target) {
                    return cnt * t == 0 ? p : 0;
                }
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.push({v, p / cnt});
                    }
                }
            }
        }
        return 0;
    }
};