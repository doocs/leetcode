class Solution {
public:
    int minCost(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        int inf = INT_MAX / 2;

        vector<vector<vector<int>>> f(k + 1, vector<vector<int>>(m, vector<int>(n, inf)));

        f[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    f[0][i][j] = min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[0][i][j] = min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        unordered_map<int, vector<pair<int, int>>> g;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                g[x].push_back({i, j});
            }
        }

        vector<int> keys;
        keys.reserve(g.size());
        for (auto& e : g) {
            keys.push_back(e.first);
        }
        sort(keys.begin(), keys.end(), greater<int>());

        for (int t = 1; t <= k; t++) {
            int mn = inf;
            for (int key : keys) {
                auto& pos = g[key];
                for (auto& p : pos) {
                    mn = min(mn, f[t - 1][p.first][p.second]);
                }
                for (auto& p : pos) {
                    f[t][p.first][p.second] = mn;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) {
                        f[t][i][j] = min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                    }
                    if (j > 0) {
                        f[t][i][j] = min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        int ans = inf;
        for (int t = 0; t <= k; t++) {
            ans = min(ans, f[t][m - 1][n - 1]);
        }
        return ans;
    }
};
