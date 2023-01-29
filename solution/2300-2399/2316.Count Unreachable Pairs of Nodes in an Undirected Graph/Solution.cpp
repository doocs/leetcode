class Solution {
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        vector<bool> vis(n);
        function<int(int)> dfs = [&](int i) -> int {
            vis[i] = true;
            int cnt = 1;
            for (int j : g[i]) {
                if (!vis[j]) {
                    cnt += dfs(j);
                }
            }
            return cnt;
        };
        long long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                long long t = dfs(i);
                ans += s * t;
                s += t;
            }
        }
        return ans;
    }
};