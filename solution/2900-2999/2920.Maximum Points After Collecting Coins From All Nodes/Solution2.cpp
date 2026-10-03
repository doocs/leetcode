class Solution {
public:
    int maximumPoints(vector<vector<int>>& edges, vector<int>& coins, int k) {
        int n = coins.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<array<int, 15>> f(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int c : g[i]) {
                    if (c != fa) {
                        stk.push_back({c, i, 0});
                    }
                }
            } else {
                for (int j = 0; j < 15; ++j) {
                    int a = (coins[i] >> j) - k;
                    int b = coins[i] >> (j + 1);
                    for (int c : g[i]) {
                        if (c != fa) {
                            a += f[c][j];
                            if (j < 14) {
                                b += f[c][j + 1];
                            }
                        }
                    }
                    f[i][j] = max(a, b);
                }
            }
        }
        return f[0][0];
    }
};
