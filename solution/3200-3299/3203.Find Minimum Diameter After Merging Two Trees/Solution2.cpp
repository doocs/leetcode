class Solution {
public:
    int minimumDiameterAfterMerge(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
        int d1 = treeDiameter(edges1);
        int d2 = treeDiameter(edges2);
        return max({d1, d2, (d1 + 1) / 2 + (d2 + 1) / 2 + 1});
    }

    int treeDiameter(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        auto farthest = [&](int start) {
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
            return pair<int, int>{ans, node};
        };
        int a = farthest(0).second;
        return farthest(a).first;
    }
};
