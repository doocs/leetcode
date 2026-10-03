class Solution {
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<char> vis(n);
        auto dfs = [&](int i) {
            if (vis[i]) {
                return 0;
            }
            vis[i] = 1;
            int cnt = 0;
            vector<int> stk{i};
            while (!stk.empty()) {
                int u = stk.back();
                stk.pop_back();
                ++cnt;
                for (int j : g[u]) {
                    if (!vis[j]) {
                        vis[j] = 1;
                        stk.push_back(j);
                    }
                }
            }
            return cnt;
        };
        long long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            int t = dfs(i);
            ans += s * t;
            s += t;
        }
        return ans;
    }
};
