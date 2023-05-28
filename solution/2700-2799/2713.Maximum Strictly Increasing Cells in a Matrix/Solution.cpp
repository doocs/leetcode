class Solution {
public:
    int maxIncreasingCells(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        map<int, vector<pair<int, int>>> g;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[mat[i][j]].emplace_back(i, j);
            }
        }
        vector<int> rowMax(m);
        vector<int> colMax(n);
        int ans = 0;
        for (auto& [_, pos] : g) {
            vector<int> mx;
            for (auto& [i, j] : pos) {
                mx.push_back(max(rowMax[i], colMax[j]) + 1);
                ans = max(ans, mx.back());
            }
            for (int k = 0; k < mx.size(); ++k) {
                auto& [i, j] = pos[k];
                rowMax[i] = max(rowMax[i], mx[k]);
                colMax[j] = max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
};