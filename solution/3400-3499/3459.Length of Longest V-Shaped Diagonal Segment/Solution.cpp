class Solution {
public:
    static constexpr int MAXN = 501;
    int f[MAXN][MAXN][4][2];

    int lenOfVDiagonal(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {1, 1, -1, -1, 1};
        memset(f, -1, sizeof(f));

        auto dfs = [&](this auto&& dfs, int i, int j, int k, int cnt) -> int {
            if (f[i][j][k][cnt] != -1) {
                return f[i][j][k][cnt];
            }
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            int target = grid[i][j] == 1 ? 2 : (2 - grid[i][j]);
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
                f[i][j][k][cnt] = 0;
                return 0;
            }
            int res = dfs(x, y, k, cnt);
            if (cnt > 0) {
                res = max(res, dfs(x, y, (k + 1) % 4, 0));
            }
            f[i][j][k][cnt] = 1 + res;
            return 1 + res;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; ++k) {
                        ans = max(ans, dfs(i, j, k, 1) + 1);
                    }
                }
            }
        }
        return ans;
    }
};
