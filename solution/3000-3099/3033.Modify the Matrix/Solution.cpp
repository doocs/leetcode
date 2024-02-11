class Solution {
public:
    vector<vector<int>> modifiedMatrix(vector<vector<int>>& matrix) {
        int r = matrix.size();
        int c = matrix[0].size();
        for (int i = 0; i < c; i++) {
            int maxs = INT_MIN;
            for (int j = 0; j < r; j++) {
                maxs = max(maxs, matrix[j][i]);
            }
            for (int j = 0; j < r; j++) {
                if (matrix[j][i] == -1) {
                    matrix[j][i] = maxs;
                }
            }
        }
        return matrix;
    }
};
