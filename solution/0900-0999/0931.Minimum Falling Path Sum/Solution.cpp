class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = matrix[i - 1][j];
                if (j > 0) mi = min(mi, matrix[i - 1][j - 1]);
                if (j < n - 1) mi = min(mi, matrix[i - 1][j + 1]);
                matrix[i][j] += mi;
            }
        }
        int res = INT_MAX;
        for (int j = 0; j < n; ++j) {
            res = min(res, matrix[n - 1][j]);
        }
        return res;
    }
};