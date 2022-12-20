class Solution {
public:
    int minTrioDegree(int n, vector<vector<int>>& edges) {
        bool g[n][n];
        memset(g, 0, sizeof g);
        int deg[n];
        memset(deg, 0, sizeof deg);
        for (auto& e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u][v] = g[v][u] = true;
            deg[u]++, deg[v]++;
        }
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (g[i][j]) {
                    for (int k = j + 1; k < n; ++k) {
                        if (g[j][k] && g[i][k]) {
                            ans = min(ans, deg[i] + deg[j] + deg[k] - 6);
                        }
                    }
                }
            }
        }
        return ans == INT_MAX ? -1 : ans;
    }
};