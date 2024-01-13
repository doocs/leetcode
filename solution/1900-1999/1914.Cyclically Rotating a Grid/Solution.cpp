class Solution {
public:
    vector<vector<int>> rotateGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        auto rotate = [&](int p, int k) {
            vector<int> nums;
            for (int j = p; j < n - p - 1; ++j) {
                nums.push_back(grid[p][j]);
            }
            for (int i = p; i < m - p - 1; ++i) {
                nums.push_back(grid[i][n - p - 1]);
            }
            for (int j = n - p - 1; j > p; --j) {
                nums.push_back(grid[m - p - 1][j]);
            }
            for (int i = m - p - 1; i > p; --i) {
                nums.push_back(grid[i][p]);
            }
            int l = nums.size();
            k %= l;
            if (k == 0) {
                return;
            }
            for (int j = p; j < n - p - 1; ++j) {
                grid[p][j] = nums[k++ % l];
            }
            for (int i = p; i < m - p - 1; ++i) {
                grid[i][n - p - 1] = nums[k++ % l];
            }
            for (int j = n - p - 1; j > p; --j) {
                grid[m - p - 1][j] = nums[k++ % l];
            }
            for (int i = m - p - 1; i > p; --i) {
                grid[i][p] = nums[k++ % l];
            }
        };
        for (int p = 0; p < min(m, n) / 2; ++p) {
            rotate(p, k);
        }
        return grid;
    }
};