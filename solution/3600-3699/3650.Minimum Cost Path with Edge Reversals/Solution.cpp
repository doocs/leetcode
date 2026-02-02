class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges) {
        using pii = pair<int, int>;
        vector<vector<pii>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].push_back({v, w});
            g[v].push_back({u, w * 2});
        }

        const int inf = INT_MAX / 2;
        vector<int> dist(n, inf);
        dist[0] = 0;

        priority_queue<pii, vector<pii>, greater<pii>> pq;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [d, u] = pq.top();
            pq.pop();
            if (d > dist[u]) {
                continue;
            }
            if (u == n - 1) {
                return d;
            }

            for (auto& [v, w] : g[u]) {
                int nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.push({nd, v});
                }
            }
        }
        return -1;
    }
};
