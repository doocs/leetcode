class Solution {
public:
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        int n = patience.size();
        vector<vector<int>> g(n);
        vector<bool> vis(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        queue<int> q{{0}};
        vis[0] = true;
        int ans = 0, step = 0;
        while (!q.empty()) {
            ++step;
            for (int i = q.size(); i > 0; --i) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (vis[v]) continue;
                    vis[v] = true;
                    q.push(v);
                    int d = step * 2, t = patience[v];
                    ans = max(ans, (d - 1) / t * t + d + 1);
                }
            }
        }
        return ans;
    }
};