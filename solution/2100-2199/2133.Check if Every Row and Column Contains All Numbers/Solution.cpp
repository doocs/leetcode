class Solution {
public:
    bool checkValid(vector<vector<int>>& matrix) {
        int n = matrix.size();
        bool vis[n + 1];
        for (const auto& row : matrix) {
            memset(vis, false, sizeof(vis));
            for (int x : row) {
                if (vis[x]) {
                    return false;
                }
                vis[x] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            memset(vis, false, sizeof(vis));
            for (int i = 0; i < n; ++i) {
                if (vis[matrix[i][j]]) {
                    return false;
                }
                vis[matrix[i][j]] = true;
            }
        }
        return true;
    }
};
