class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int idx = (i * n + j + k) % (m * n);
                int x = idx / n, y = idx % n;
                ans[x][y] = grid[i][j];
            }
        }
        return ans;
    }
};