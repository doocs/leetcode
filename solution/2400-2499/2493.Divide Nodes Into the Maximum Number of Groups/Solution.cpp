class Solution {
public:
    int magnificentSets(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        vector<int> arr;
        bool vis[n + 1];
        memset(vis, 0, sizeof vis);
        int ans = 0;
        function<void(int)> dfs = [&](int i) {
            arr.emplace_back(i);
            vis[i] = true;
            for (int& j : g[i]) {
                if (!vis[j]) {
                    dfs(j);
                }
            }
        };
        auto bfs = [&](int k) {
            int ans = 1;
            int dist[n + 1];
            memset(dist, 0x3f, sizeof dist);
            dist[k] = 1;
            queue<int> q{{k}};
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                for (int& j : g[i]) {
                    if (dist[j] == 0x3f3f3f3f) {
                        ans = dist[j] = dist[i] + 1;
                        q.push(j);
                    }
                }
            }
            for (int& i : arr) {
                if (dist[i] == 0x3f3f3f3f) {
                    dist[i] = ++ans;
                }
            }
            for (int& i : arr) {
                for (int& j : g[i]) {
                    if (abs(dist[i] - dist[j]) != 1) {
                        return -1;
                    }
                }
            }
            return ans;
        };
        for (int i = 1; i <= n; ++i) {
            if (!vis[i]) {
                dfs(i);
                int t = -1;
                for (int& v : arr) t = max(t, bfs(v));
                if (t == -1) return -1;
                ans += t;
                arr.clear();
            }
        }
        return ans;
    }
};