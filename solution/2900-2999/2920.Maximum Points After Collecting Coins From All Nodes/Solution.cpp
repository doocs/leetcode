class Solution {
public:
    int maximumPoints(vector<vector<int>>& edges, vector<int>& coins, int k) {
        int n = coins.size();
        int f[n][15];
        memset(f, -1, sizeof(f));
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        auto dfs = [&](this auto&& dfs, int i, int fa, int j) -> int {
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int a = (coins[i] >> j) - k;
            int b = coins[i] >> (j + 1);
            for (int c : g[i]) {
                if (c != fa) {
                    a += dfs(c, i, j);
                    if (j < 14) {
                        b += dfs(c, i, j + 1);
                    }
                }
            }
            return f[i][j] = max(a, b);
        };
        return dfs(0, -1, 0);
    }
};
