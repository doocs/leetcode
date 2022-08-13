class Solution {
public:
    int shortestPathLength(vector<vector<int>>& graph) {
        int n = graph.size();
        queue<tuple<int, int, int>> q;
        vector<vector<bool>> vis(n, vector<bool>(1 << n));
        for (int i = 0; i < n; ++i) {
            q.emplace(i, 1 << i, 0);
            vis[i][1 << i] = true;
        }
        while (!q.empty()) {
            auto [u, state, dist] = q.front();
            q.pop();
            if (state == (1 << n) - 1) return dist;
            for (int& v : graph[u]) {
                int nxt = state | (1 << v);
                if (!vis[v][nxt]) {
                    q.emplace(v, nxt, dist + 1);
                    vis[v][nxt] = true;
                }
            }
        }
        return 0;
    }
};