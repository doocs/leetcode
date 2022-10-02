class Solution {
public:
    int maxSum(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                int t = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        t += grid[x][y];
                    }
                }
                t -= grid[i][j - 1];
                t -= grid[i][j + 1];
                ans = max(ans, t);
            }
        }
        return ans;
    }
};