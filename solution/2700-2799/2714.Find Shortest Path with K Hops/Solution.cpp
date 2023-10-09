class Solution {
public:
    int shortestPathWithHops(int n, vector<vector<int>>& edges, int s, int d, int k) {
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
        pq.emplace(0, s, 0);
        int dist[n][k + 1];
        memset(dist, 0x3f, sizeof(dist));
        dist[s][0] = 0;
        while (!pq.empty()) {
            auto [dis, u, t] = pq.top();
            pq.pop();
            for (auto [v, w] : g[u]) {
                if (t + 1 <= k && dist[v][t + 1] > dis) {
                    dist[v][t + 1] = dis;
                    pq.emplace(dis, v, t + 1);
                }
                if (dist[v][t] > dis + w) {
                    dist[v][t] = dis + w;
                    pq.emplace(dis + w, v, t);
                }
            }
        }
        return *min_element(dist[d], dist[d] + k + 1);
    }
};