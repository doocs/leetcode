class Solution {
public:
    int minPathCost(vector<vector<int>>& grid, vector<vector<int>>& moveCost) {
        int m = grid.size(), n = grid[0].size();
        const int inf = 1 << 30;
        vector<int> f = grid[0];
        for (int i = 1; i < m; ++i) {
            vector<int> g(n, inf);
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[j] = min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};