class Solution {
public:
    int projectionArea(vector<vector<int>>& grid) {
        int xy = 0, yz = 0, zx = 0;
        for (int i = 0, n = grid.size(); i < n; ++i) {
            int maxYz = 0, maxZx = 0;
            for (int j = 0; j < n; ++j) {
                xy += grid[i][j] > 0;
                maxYz = max(maxYz, grid[i][j]);
                maxZx = max(maxZx, grid[j][i]);
            }
            yz += maxYz;
            zx += maxZx;
        }
        return xy + yz + zx;
    }
};