class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m - k + 1, vector<int>(n - k + 1, 0));
        for (int i = 0; i <= m - k; ++i) {
            for (int j = 0; j <= n - k; ++j) {
                vector<int> nums;
                for (int x = i; x < i + k; ++x) {
                    for (int y = j; y < j + k; ++y) {
                        nums.push_back(grid[x][y]);
                    }
                }
                sort(nums.begin(), nums.end());
                int d = INT_MAX;
                for (int t = 1; t < nums.size(); ++t) {
                    if (nums[t] != nums[t - 1]) {
                        d = min(d, abs(nums[t] - nums[t - 1]));
                    }
                }
                ans[i][j] = (d == INT_MAX) ? 0 : d;
            }
        }
        return ans;
    }
};
