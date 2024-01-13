class Solution {
public:
    int numberOfSets(int n, int maxDistance, vector<vector<int>>& roads) {
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int g[n][n];
            memset(g, 0x3f, sizeof(g));
            for (auto& e : roads) {
                int u = e[0], v = e[1], w = e[2];
                if ((mask >> u & 1) & (mask >> v & 1)) {
                    g[u][v] = min(g[u][v], w);
                    g[v][u] = min(g[v][u], w);
                }
            }
            for (int k = 0; k < n; ++k) {
                if (mask >> k & 1) {
                    g[k][k] = 0;
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                        }
                    }
                }
            }
            int ok = 1;
            for (int i = 0; i < n && ok == 1; ++i) {
                for (int j = 0; j < n && ok == 1; ++j) {
                    if ((mask >> i & 1) & (mask >> j & 1) && g[i][j] > maxDistance) {
                        ok = 0;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
};