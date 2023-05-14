class Solution {
public:
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        bool vis[n];
        memset(vis, false, sizeof(vis));
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<pair<int, int>(int)> dfs = [&](int i) -> pair<int, int> {
            vis[i] = true;
            int x = 1, y = g[i].size();
            for (int j : g[i]) {
                if (!vis[j]) {
                    auto [a, b] = dfs(j);
                    x += a;
                    y += b;
                }
            }
            return make_pair(x, y);
        };
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                auto [a, b] = dfs(i);
                if (a * (a - 1) == b) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};