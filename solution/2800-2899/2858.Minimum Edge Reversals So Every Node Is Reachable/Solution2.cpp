class Solution {
public:
    vector<int> minEdgeReversals(int n, vector<vector<int>>& edges) {
        vector<vector<pair<int, int>>> g(n);
        vector<int> ans(n);
        for (auto& e : edges) {
            int x = e[0], y = e[1];
            g[x].emplace_back(y, 1);
            g[y].emplace_back(x, -1);
        }
        vector<pair<int, int>> stk{{0, -1}};
        while (!stk.empty()) {
            auto [i, fa] = stk.back();
            stk.pop_back();
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[0] += k < 0;
                    stk.emplace_back(j, i);
                }
            }
        }
        stk.emplace_back(0, -1);
        while (!stk.empty()) {
            auto [i, fa] = stk.back();
            stk.pop_back();
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[j] = ans[i] + k;
                    stk.emplace_back(j, i);
                }
            }
        }
        return ans;
    }
};
