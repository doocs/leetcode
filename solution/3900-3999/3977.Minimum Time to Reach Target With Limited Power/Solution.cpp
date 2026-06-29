class Solution {
public:
    vector<long long> minTimeMaxPower(
        int n,
        vector<vector<int>>& edges,
        int power,
        vector<int>& cost,
        int source,
        int target) {
        using ll = long long;
        const ll inf = LLONG_MAX / 4;

        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            g[e[0]].push_back({e[1], e[2]});
        }

        vector<vector<ll>> dist(n, vector<ll>(power + 1, inf));

        using T = tuple<ll, int, int>;
        priority_queue<T, vector<T>, greater<T>> pq;

        dist[source][power] = 0;
        pq.push({0, -power, source});

        while (!pq.empty()) {
            auto [d, negp, u] = pq.top();
            pq.pop();

            int p = -negp;

            if (u == target) return {d, p};
            if (d > dist[u][p] || p < cost[u]) continue;

            p -= cost[u];

            for (auto& [v, t] : g[u]) {
                ll nd = d + t;

                if (nd < dist[v][p]) {
                    dist[v][p] = nd;
                    pq.push({nd, -p, v});
                }
            }
        }

        return {-1, -1};
    }
};