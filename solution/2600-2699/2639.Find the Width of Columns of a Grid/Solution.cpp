class Solution {
public:
    vector<int> findColumnWidth(vector<vector<int>>& grid) {
        int n = grid[0].size();
        vector<int> ans(n);
        for (auto& row : grid) {
            for (int j = 0; j < n; ++j) {
                int w = to_string(row[j]).size();
                ans[j] = max(ans[j], w);
            }
        }
        return ans;
    }
};