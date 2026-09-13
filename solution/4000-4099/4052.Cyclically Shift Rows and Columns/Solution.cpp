class Solution {
public:
    vector<vector<int>> cyclicShift(int n, vector<vector<int>>& grid, vector<int>& rowShift, vector<int>& colShift) {
        vector<vector<int>> t(n, vector<int>(n));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t[i][(j - rowShift[i] + n) % n] = grid[i][j];
            }
        }
        vector<vector<int>> ans(n, vector<int>(n));
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                ans[(i - colShift[j] + n) % n][j] = t[i][j];
            }
        }
        return ans;
    }
};
