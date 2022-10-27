class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j]) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (auto& row : matrix) {
            sort(row.rbegin(), row.rend());
            for (int j = 0; j < n; ++j) {
                ans = max(ans, (j + 1) * row[j]);
            }
        }
        return ans;
    }
};