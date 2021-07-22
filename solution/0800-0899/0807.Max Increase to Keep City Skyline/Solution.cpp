class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> we(m, 0);
        vector<int> ns(n, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                we[i] = max(we[i], grid[i][j]);
                ns[j] = max(ns[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += min(we[i], ns[j]) - grid[i][j];
            }
        }
        return res;
    }
};