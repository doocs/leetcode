class Solution {
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        vector<int> size(n);
        vector<array<int, 4>> stk{{0, -1, 0, 0}};
        while (!stk.empty()) {
            auto [i, fa, d, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                ans[0] += d;
                stk.push_back({i, fa, d, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, d + 1, 0});
                    }
                }
            } else {
                size[i] = 1;
                for (int j : g[i]) {
                    if (j != fa) {
                        size[i] += size[j];
                    }
                }
            }
        }
        vector<array<int, 3>> walk{{0, -1, ans[0]}};
        while (!walk.empty()) {
            auto [i, fa, t] = walk.back();
            walk.pop_back();
            ans[i] = t;
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push_back({j, i, t - size[j] + n - size[j]});
                }
            }
        }
        return ans;
    }
};
