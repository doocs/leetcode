class Solution {
public:
    vector<vector<int>> diagonalSort(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> g[m + n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[m - i + j].push_back(mat[i][j]);
            }
        }
        for (auto& e : g) {
            sort(e.rbegin(), e.rend());
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                mat[i][j] = g[m - i + j].back();
                g[m - i + j].pop_back();
            }
        }
        return mat;
    }
};