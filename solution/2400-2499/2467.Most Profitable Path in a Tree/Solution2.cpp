class Solution {
public:
    int mostProfitablePath(vector<vector<int>>& edges, int bob, vector<int>& amount) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        vector<int> parent(n, -1);
        vector<char> seen(n);
        seen[0] = 1;
        vector<int> stk{0};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            for (int j : g[i]) {
                if (!seen[j]) {
                    seen[j] = 1;
                    parent[j] = i;
                    stk.push_back(j);
                }
            }
        }
        vector<int> ts(n, n);
        for (int x = bob, t = 0; x != -1; x = parent[x], ++t) {
            ts[x] = t;
        }
        int ans = INT_MIN;
        vector<array<int, 4>> walk{{0, -1, 0, 0}};
        while (!walk.empty()) {
            auto [i, fa, t, v] = walk.back();
            walk.pop_back();
            if (t == ts[i]) {
                v += amount[i] >> 1;
            } else if (t < ts[i]) {
                v += amount[i];
            }
            if (g[i].size() == 1 && g[i][0] == fa) {
                ans = max(ans, v);
                continue;
            }
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push_back({j, i, t + 1, v});
                }
            }
        }
        return ans;
    }
};
