class Solution {
public:
    int reachableNodes(vector<vector<int>>& edges, int maxMoves, int n) {
        using pii = pair<int, int>;
        vector<vector<pii>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], cnt = e[2] + 1;
            g[u].emplace_back(v, cnt);
            g[v].emplace_back(u, cnt);
        }
        priority_queue<pii, vector<pii>, greater<pii>> q;
        q.emplace(0, 0);
        int dist[n];
        memset(dist, 0x3f, sizeof dist);
        dist[0] = 0;
        while (!q.empty()) {
            auto [d, u] = q.top();
            q.pop();
            for (auto& [v, cnt] : g[u]) {
                if (d + cnt < dist[v]) {
                    dist[v] = d + cnt;
                    q.emplace(dist[v], v);
                }
            }
        }
        int ans = 0;
        for (int& d : dist) ans += d <= maxMoves;
        for (auto& e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int a = min(cnt, max(0, maxMoves - dist[u]));
            int b = min(cnt, max(0, maxMoves - dist[v]));
            ans += min(cnt, a + b);
        }
        return ans;
    }
};