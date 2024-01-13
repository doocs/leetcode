class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<int(int, int)> dfs = [&](int i, int j) {
            int ok = grid1[i][j];
            grid2[i][j] = 0;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                    ok &= dfs(x, y);
                }
            }
            return ok;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j]) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }
};