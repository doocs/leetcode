class Solution {
public:
    long long minCostExcludingMax(int n, vector<vector<int>>& edges) {
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].push_back({v, w});
            g[v].push_back({u, w});
        }

        long long inf = LLONG_MAX / 4;
        vector<array<long long, 2>> dist(n, {inf, inf});
        dist[0][0] = 0;

        priority_queue<array<long long, 3>, vector<array<long long, 3>>, greater<array<long long, 3>>> pq;
        pq.push({0, 0, 0});

        while (!pq.empty()) {
            auto t = pq.top();
            pq.pop();
            long long cur = t[0];
            int u = t[1];
            int used = t[2];

            if (cur > dist[u][used]) {
                continue;
            }
            if (u == n - 1 && used == 1) {
                return cur;
            }

            for (auto [v, w] : g[u]) {
                long long nxt = cur + w;
                if (nxt < dist[v][used]) {
                    dist[v][used] = nxt;
                    pq.push({nxt, v, used});
                }

                if (used == 0) {
                    nxt = cur;
                    if (nxt < dist[v][1]) {
                        dist[v][1] = nxt;
                        pq.push({nxt, v, 1});
                    }
                }
            }
        }

        return dist[n - 1][1];
    }
};
