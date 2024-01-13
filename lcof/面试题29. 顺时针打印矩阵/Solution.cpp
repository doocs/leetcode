class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0) {
            return {};
        }
        int m = matrix.size(), n = matrix[0].size();
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        int i = 0, j = 0, k = 0;
        int dirs[5] = {0, 1, 0, -1, 0};
        vector<int> ans(m * n);
        for (int h = 0; h < m * n; ++h) {
            ans[h] = matrix[i][j];
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k];
                y = j + dirs[k + 1];
            }
            i = x;
            j = y;
        }
        return ans;
    }
};