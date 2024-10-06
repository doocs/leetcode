class Solution {
public:
    vector<int> remainingMethods(int n, int k, vector<vector<int>>& invocations) {
        vector<bool> suspicious(n);
        vector<bool> vis(n);
        vector<int> f[n];
        vector<int> g[n];
        for (const auto& e : invocations) {
            int a = e[0], b = e[1];
            f[a].push_back(b);
            f[b].push_back(a);
            g[a].push_back(b);
        }
        auto dfs = [&](auto&& dfs, int i) -> void {
            suspicious[i] = true;
            for (int j : g[i]) {
                if (!suspicious[j]) {
                    dfs(dfs, j);
                }
            }
        };
        dfs(dfs, k);
        auto dfs2 = [&](auto&& dfs2, int i) -> void {
            vis[i] = true;
            for (int j : f[i]) {
                if (!vis[j]) {
                    suspicious[j] = false;
                    dfs2(dfs2, j);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i] && !vis[i]) {
                dfs2(dfs2, i);
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
