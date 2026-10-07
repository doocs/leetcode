class Solution {
public:
    int treeDiameter(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        auto farthest = [&](int start) -> pair<int, int> {
            int ans = 0, node = start;
            vector<array<int, 3>> stk{{start, -1, 0}};
            while (!stk.empty()) {
                auto [i, fa, t] = stk.back();
                stk.pop_back();
                if (ans < t) {
                    ans = t;
                    node = i;
                }
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, t + 1});
                    }
                }
            }
            return {node, ans};
        };
        int node = farthest(0).first;
        return farthest(node).second;
    }
};
