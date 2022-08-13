class Solution {
public:
    int minPathCost(vector<vector<int>>& grid, vector<vector<int>>& moveCost) {
        int m = grid.size(), n = grid[0].size();
        int inf = INT_MAX;
        vector<int> f(n);
        for (int i = 0; i < m; ++i) {
            vector<int> g(n);
            for (int j = 0; j < n; ++j) {
                g[j] = grid[i][j];
                int t = inf;
                if (i) {
                    for (int k = 0; k < n; ++k) {
                        t = min(t, f[k] + moveCost[grid[i - 1][k]][j]);
                    }
                }
                if (t != inf) g[j] += t;
            }
            f = g;
        }
        return *min_element(f.begin(), f.end());
    }
};