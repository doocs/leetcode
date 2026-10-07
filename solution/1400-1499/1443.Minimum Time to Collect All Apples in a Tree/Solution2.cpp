class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<int> cost(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [u, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({u, fa, 1});
                for (int v : g[u]) {
                    if (v != fa) {
                        stk.push_back({v, u, 0});
                    }
                }
            } else {
                int nxt = 0;
                for (int v : g[u]) {
                    if (v != fa) {
                        nxt += cost[v];
                    }
                }
                if (hasApple[u] || nxt) {
                    cost[u] = u == 0 ? nxt : nxt + 2;
                }
            }
        }
        return cost[0];
    }
};
