class Solution {
public:
    int findShortestCycle(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        const int inf = 1 << 30;
        auto bfs = [&](int u) -> int {
            int dist[n];
            memset(dist, -1, sizeof(dist));
            dist[u] = 0;
            queue<pair<int, int>> q;
            q.emplace(u, -1);
            int ans = inf;
            while (!q.empty()) {
                auto p = q.front();
                u = p.first;
                int fa = p.second;
                q.pop();
                for (int v : g[u]) {
                    if (dist[v] < 0) {
                        dist[v] = dist[u] + 1;
                        q.emplace(v, u);
                    } else if (v != fa) {
                        ans = min(ans, dist[u] + dist[v] + 1);
                    }
                }
            }
            return ans;
        };
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            ans = min(ans, bfs(i));
        }
        return ans < inf ? ans : -1;
    }
};