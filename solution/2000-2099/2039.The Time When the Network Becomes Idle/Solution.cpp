class Solution {
public:
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        int n = patience.size();
        vector<int> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        queue<int> q{{0}};
        bool vis[n];
        memset(vis, false, sizeof(vis));
        vis[0] = true;
        int ans = 0, d = 0;
        while (!q.empty()) {
            ++d;
            int t = d * 2;
            for (int i = q.size(); i; --i) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.push(v);
                        ans = max(ans, (t - 1) / patience[v] * patience[v] + t + 1);
                    }
                }
            }
        }
        return ans;
    }
};