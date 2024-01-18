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
        auto bfs = [&](int u, int v) -> int {
            int dist[n];
            fill(dist, dist + n, inf);
            dist[u] = 0;
            queue<int> q{{u}};
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if ((i == u && j == v) || (i == v && j == u) || dist[j] != inf) {
                        continue;
                    }
                    dist[j] = dist[i] + 1;
                    q.push(j);
                }
            }
            return dist[v] + 1;
        };
        int ans = inf;
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            ans = min(ans, bfs(u, v));
        }
        return ans < inf ? ans : -1;
    }
};