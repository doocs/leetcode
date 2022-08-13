class Solution {
public:
    double frogPosition(int n, vector<vector<int>>& edges, int t, int target) {
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        typedef pair<int, double> pid;
        queue<pid> q;
        q.push({1, 1.0});
        vector<bool> vis(n + 1);
        vis[1] = true;
        while (!q.empty() && t >= 0) {
            for (int k = q.size(); k; --k) {
                auto x = q.front();
                q.pop();
                int u = x.first;
                double p = x.second;
                vector<int> nxt;
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nxt.push_back(v);
                    }
                }
                if (u == target && (t == 0 || nxt.empty())) return p;
                for (int v : nxt) q.push({v, p / nxt.size()});
            }
            --t;
        }
        return 0;
    }
};