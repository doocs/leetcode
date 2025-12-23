class Solution {
public:
    string findSpecialNodes(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }

        auto bfs = [&](int start) -> pair<int, vector<int>> {
            vector<int> dist(n, -1);
            dist[start] = 0;
            deque<int> q;
            q.push_back(start);
            int far = start;
            while (!q.empty()) {
                int u = q.front();
                q.pop_front();
                if (dist[u] > dist[far]) {
                    far = u;
                }
                for (int v : g[u]) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q.push_back(v);
                    }
                }
            }
            return {far, dist};
        };

        auto [a, _0] = bfs(0);
        auto [b, dist1] = bfs(a);
        auto [_1, dist2] = bfs(b);
        int d = dist1[b];

        string ans(n, '0');
        for (int i = 0; i < n; i++) {
            if (dist1[i] == d || dist2[i] == d) {
                ans[i] = '1';
            }
        }
        return ans;
    }
};
