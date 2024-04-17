class Solution {
public:
    vector<int> minimumTime(int n, vector<vector<int>>& edges, vector<int>& disappear) {
        vector<vector<pair<int, int>>> g(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].push_back({v, w});
            g[v].push_back({u, w});
        }

        vector<int> dist(n, 1 << 30);
        dist[0] = 0;

        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [du, u] = pq.top();
            pq.pop();

            if (du > dist[u]) {
                continue;
            }

            for (auto [v, w] : g[u]) {
                if (dist[v] > dist[u] + w && dist[u] + w < disappear[v]) {
                    dist[v] = dist[u] + w;
                    pq.push({dist[v], v});
                }
            }
        }

        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = dist[i] < disappear[i] ? dist[i] : -1;
        }

        return ans;
    }
};