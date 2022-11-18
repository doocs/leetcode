using ll = long long;
using pii = pair<int, int>;

class Solution {
public:
    const int inf = 0x3f3f3f3f;

    vector<long long> minCost(int n, vector<vector<int>>& roads, vector<int>& appleCost, int k) {
        vector<vector<pii>> g(n);
        for (auto& e : roads) {
            int a = e[0] - 1, b = e[1] - 1, c = e[2];
            g[a].push_back({b, c});
            g[b].push_back({a, c});
        }
        int dist[n];
        auto dijkstra = [&](int u) {
            memset(dist, 63, sizeof dist);
            priority_queue<pii, vector<pii>, greater<pii>> q;
            q.push({0, u});
            dist[u] = 0;
            ll ans = LONG_MAX;
            while (!q.empty()) {
                auto p = q.top();
                q.pop();
                int d = p.first;
                u = p.second;
                ans = min(ans, appleCost[u] + 1ll * d * (k + 1));
                for (auto& ne : g[u]) {
                    auto [v, w] = ne;
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        q.push({dist[v], v});
                    }
                }
            }
            return ans;
        };
        vector<ll> ans(n);
        for (int i = 0; i < n; ++i) ans[i] = dijkstra(i);
        return ans;
    }
};