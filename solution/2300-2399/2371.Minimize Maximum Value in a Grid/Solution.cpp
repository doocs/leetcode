class Solution {
public:
    vector<vector<int>> minScore(vector<vector<int>>& grid) {
        vector<tuple<int, int, int>> nums;
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nums.push_back({grid[i][j], i, j});
            }
        }
        sort(nums.begin(), nums.end());
        vector<int> rowMax(m);
        vector<int> colMax(n);
        vector<vector<int>> ans(m, vector<int>(n));
        for (auto [_, i, j] : nums) {
            ans[i][j] = max(rowMax[i], colMax[j]) + 1;
            rowMax[i] = colMax[j] = ans[i][j];
        }
        return ans;
    }
};