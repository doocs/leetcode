class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int x1 = 0, y1 = 0, x2 = m - 1, y2 = n - 1;
        vector<int> ans;
        while (x1 <= x2 && y1 <= y2) {
            for (int j = y1; j <= y2; ++j) {
                ans.push_back(matrix[x1][j]);
            }
            for (int i = x1 + 1; i <= x2; ++i) {
                ans.push_back(matrix[i][y2]);
            }
            if (x1 < x2 && y1 < y2) {
                for (int j = y2 - 1; j >= y1; --j) {
                    ans.push_back(matrix[x2][j]);
                }
                for (int i = x2 - 1; i > x1; --i) {
                    ans.push_back(matrix[i][y1]);
                }
            }
            ++x1, ++y1;
            --x2, --y2;
        }
        return ans;
    }
};