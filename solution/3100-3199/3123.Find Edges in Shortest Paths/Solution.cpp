class Solution {
public:
    vector<bool> findAnswer(int n, vector<vector<int>>& edges) {
        vector<vector<array<int, 3>>> g(n);
        int m = edges.size();
        for (int i = 0; i < m; ++i) {
            auto e = edges[i];
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w, i});
            g[b].push_back({a, w, i});
        }
        const int inf = 1 << 30;
        vector<int> dist(n, inf);
        dist[0] = 0;

        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [da, a] = pq.top();
            pq.pop();
            if (da > dist[a]) {
                continue;
            }

            for (auto [b, w, _] : g[a]) {
                if (dist[b] > dist[a] + w) {
                    dist[b] = dist[a] + w;
                    pq.push({dist[b], b});
                }
            }
        }
        vector<bool> ans(m);
        if (dist[n - 1] == inf) {
            return ans;
        }
        queue<int> q{{n - 1}};
        while (!q.empty()) {
            int a = q.front();
            q.pop();
            for (auto [b, w, i] : g[a]) {
                if (dist[a] == dist[b] + w) {
                    ans[i] = true;
                    q.push(b);
                }
            }
        }
        return ans;
    }
};