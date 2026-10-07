using pii = pair<int, int>;

class Solution {
public:
    int countRestrictedPaths(int n, vector<vector<int>>& edges) {
        const int inf = INT_MAX;
        const int mod = 1e9 + 7;
        vector<vector<pii>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        vector<int> dist(n + 1, inf);
        dist[n] = 0;
        priority_queue<pii, vector<pii>, greater<pii>> q;
        q.emplace(0, n);
        while (!q.empty()) {
            auto [_, u] = q.top();
            q.pop();
            for (auto [v, w] : g[u]) {
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.emplace(dist[v], v);
                }
            }
        }
        vector<int> order(n);
        iota(order.begin(), order.end(), 1);
        sort(order.begin(), order.end(), [&](int a, int b) { return dist[a] < dist[b]; });
        vector<int> f(n + 1);
        f[n] = 1;
        for (int i : order) {
            for (auto [j, _] : g[i]) {
                if (dist[i] > dist[j]) {
                    f[i] = (f[i] + f[j]) % mod;
                }
            }
        }
        return f[1];
    }
};
