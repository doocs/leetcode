class Solution {
public:
    vector<vector<int>> diagonalSort(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        for (int k = 0; k < min(m, n) - 1; ++k)
            for (int i = 0; i < m - 1; ++i)
                for (int j = 0; j < n - 1; ++j)
                    if (mat[i][j] > mat[i + 1][j + 1])
                        swap(mat[i][j], mat[i + 1][j + 1]);
        return mat;
    }
};