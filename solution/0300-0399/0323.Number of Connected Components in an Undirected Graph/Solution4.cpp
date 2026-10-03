class Solution {
public:
    int countComponents(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<char> vis(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            ++ans;
            vector<int> stk = {i};
            vis[i] = 1;
            while (!stk.empty()) {
                int u = stk.back();
                stk.pop_back();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = 1;
                        stk.push_back(v);
                    }
                }
            }
        }
        return ans;
    }
};
