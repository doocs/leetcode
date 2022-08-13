class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rmx(m, 0);
        vector<int> cmx(n, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rmx[i] = max(rmx[i], grid[i][j]);
                cmx[j] = max(cmx[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans += min(rmx[i], cmx[j]) - grid[i][j];
        return ans;
    }
};