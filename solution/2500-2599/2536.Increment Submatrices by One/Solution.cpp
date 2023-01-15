class Solution {
public:
    vector<vector<int>> rangeAddQueries(int n, vector<vector<int>>& queries) {
        vector<vector<int>> mat(n, vector<int>(n));
        for (auto& q : queries) {
            int x1 = q[0], y1 = q[1], x2 = q[2], y2 = q[3];
            mat[x1][y1]++;
            if (x2 + 1 < n) {
                mat[x2 + 1][y1]--;
            }
            if (y2 + 1 < n) {
                mat[x1][y2 + 1]--;
            }
            if (x2 + 1 < n && y2 + 1 < n) {
                mat[x2 + 1][y2 + 1]++;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    mat[i][j] += mat[i - 1][j];
                }
                if (j > 0) {
                    mat[i][j] += mat[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    mat[i][j] -= mat[i - 1][j - 1];
                }
            }
        }
        return mat;
    }
};