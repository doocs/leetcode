class Solution {
public:
    bool possibleToStamp(vector<vector<int>>& grid, int stampHeight, int stampWidth) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        vector<vector<int>> d(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) continue;
                int x = i + stampHeight, y = j + stampWidth;
                if (x <= m && y <= n && s[x][y] - s[i][y] - s[x][j] + s[i][j] == 0) {
                    d[i][j]++;
                    d[x][j]--;
                    d[i][y]--;
                    d[x][y]++;
                }
            }
        }
        vector<vector<int>> cnt(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
                if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) return false;
            }
        }
        return true;
    }
};